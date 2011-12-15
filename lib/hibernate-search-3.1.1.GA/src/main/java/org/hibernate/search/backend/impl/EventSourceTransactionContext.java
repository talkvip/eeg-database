// $Id: EventSourceTransactionContext.java 16392 2009-04-22 06:52:05Z sannegrinovero $
package org.hibernate.search.backend.impl;

import java.io.Serializable;

import javax.transaction.Synchronization;

import org.hibernate.Transaction;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEventListener;
import org.hibernate.search.SearchException;
import org.hibernate.search.backend.TransactionContext;
import org.hibernate.search.event.FullTextIndexEventListener;
import org.hibernate.search.util.LoggerFactory;
import org.slf4j.Logger;

/**
 * Implementation of the transactional context on top of an EventSource (Session)
 * 
 * @author Navin Surtani  - navin@surtani.org
 * @author Emmanuel Bernard
 * @author Sanne Grinovero
 */
public class EventSourceTransactionContext implements TransactionContext, Serializable {
	
	private static final Logger log = LoggerFactory.make();
	
	private final EventSource eventSource;
	
	//this transient is required to break recursive serialization
	private transient FullTextIndexEventListener flushListener;

	//constructor time is too early to define the value of realTxInProgress,
	//postpone it, otherwise doing
	// " openSession - beginTransaction "
	//will behave as "out of transaction" in the whole session lifespan.
	private Boolean realTxInProgress = null;
	
	public EventSourceTransactionContext(EventSource eventSource) {
		this.eventSource = eventSource;
		this.flushListener = getIndexWorkFlushEventListener();
	}

	public Object getTransactionIdentifier() {
		if ( isRealTransactionInProgress() ) {
			return eventSource.getTransaction();
		}
		else {
			return eventSource;
		}
	}

	public void registerSynchronization(Synchronization synchronization) {
		if ( isRealTransactionInProgress() ) {
			Transaction transaction = eventSource.getTransaction();
			transaction.registerSynchronization( synchronization );
		}
		else {
			//registerSynchronization is only called if isRealTransactionInProgress or if
			// a flushListener was found; still we might need to find the listener again
			// as it might have been cleared by serialization (is transient).
			flushListener = getIndexWorkFlushEventListener();
			if ( flushListener != null ) {
				flushListener.addSynchronization( eventSource, synchronization );
			}
			else {
				//shouldn't happen if the code about serialization is fine:
				throw new SearchException( "AssertionFailure: flushListener not registered any more.");
			}
		}
	}
	
	private FullTextIndexEventListener getIndexWorkFlushEventListener() {
		if ( this.flushListener != null) {
			//for the "transient" case: might have been nullified.
			return flushListener;
		}
		FlushEventListener[] flushEventListeners = eventSource.getListeners().getFlushEventListeners();
		for (FlushEventListener listener : flushEventListeners) {
			if ( listener.getClass().equals( FullTextIndexEventListener.class ) ) {
				return (FullTextIndexEventListener) listener;
			}
		}
		log.debug( "No FullTextIndexEventListener was registered" );
		return null;
	}

	//The code is not really fitting the method name;
	//(unless you consider a flush as a mini-transaction)
	//This is because we want to behave as "inTransaction" if the flushListener is registered.
	public boolean isTransactionInProgress() {
		// either it is a real transaction, or if we are capable to manage this in the IndexWorkFlushEventListener
		return getIndexWorkFlushEventListener() != null || isRealTransactionInProgress();
	}
	
	private boolean isRealTransactionInProgress() {
		if ( realTxInProgress == null ) {
			realTxInProgress = eventSource.isTransactionInProgress();
		}
		return realTxInProgress;
	}
	
}