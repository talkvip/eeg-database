package cz.zcu.kiv.eegdatabase.logic.util;

import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Honza
 * Date: 31.5.2011
 * Time: 13:42:41
 * To change this template use File | Settings | File Templates.
 */
public class SignalProcessingUtils {

       public static boolean isSuitableExperiment(Experiment e) {
        boolean vhdr = false;
        boolean eeg = false;
        Set<DataFile> files = e.getDataFiles();
        for (DataFile file : files) {
            if (file.getFilename().endsWith(".vhdr")) {
                vhdr = true;
            }
            if ((file.getFilename().endsWith(".eeg")) || (file.getFilename().endsWith(".avg"))) {
                eeg = true;
            }
        }
        return (vhdr && eeg);
    }

    public static void splitExperimentToView(ModelAndView mav, List<Experiment> list) {
        List<Experiment> suitable = new ArrayList<Experiment>();
        List<Experiment> notSuitable = new ArrayList<Experiment>();
        for (Experiment e: list) {
            if (isSuitableExperiment(e)) {
                suitable.add(e);
            }
            else {
                notSuitable.add(e);
            }
        }
        mav.addObject("suitable", suitable);
        mav.addObject("notSuitable", notSuitable);
    }
}