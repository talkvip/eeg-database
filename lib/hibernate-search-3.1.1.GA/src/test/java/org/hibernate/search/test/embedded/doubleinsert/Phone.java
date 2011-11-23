// $Id: Phone.java 14954 2008-07-17 20:43:10Z sannegrinovero $
package org.hibernate.search.test.embedded.doubleinsert;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name="T_PHONE")
@Indexed
public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="P_PHONE_ID")
	@DocumentId
	private long id;

	@Column(name="P_NUMBER")
	@Field(index=Index.TOKENIZED, store=Store.YES)
	private String number;

	@Column(name="P_TYPE")
	@Field(index=Index.TOKENIZED, store=Store.YES)
	private String type;

	@Column(name="P_CREATEDON")
	@Type(type="java.util.Date")
	private Date createdOn;

	@Column(name="P_LASTUPDATEDON")
	@Type(type="java.util.Date")
	private Date lastUpdatedOn;

	@ManyToOne
	@JoinColumn(name="C_CONTACT_ID")
	@IndexedEmbedded
	private Contact contact;


	public Phone() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		if (null == this.type || "".equals(this.type)) {
			return "N/A";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}


	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}


//	public int hashCode() {
//		return new HashCodeBuilder().append(new Object[]{this.number, this.type}).hashCode();
//	}
//
//	public boolean equals(Object object) {
//		if (!(object instanceof Phone)) {
//			return false;
//		}
//
//		return new EqualsBuilder().append(new Object[]{}, new Object[]{}).isEquals();
//	}
//
//	public String toString() {
//		StringBuffer buf = new StringBuffer();
//		displayPhoneDetails(buf, this);
//		return buf.toString();
//	}

	private void displayPhoneDetails(StringBuffer buf, Phone phone) {
//		buf.append(Constants.TAB + Constants.TAB + "Type: " + phone.getType() );
//		buf.append(Constants.SPACE + "Number: " + phone.getNumber() + Constants.NEW_LINE);
	}
}