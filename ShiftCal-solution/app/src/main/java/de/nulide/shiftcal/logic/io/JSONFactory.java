package de.nulide.shiftcal.logic.io;

import de.nulide.shiftcal.logic.io.object.JSONCalendarDate;
import de.nulide.shiftcal.logic.io.object.JSONEmployer;
import de.nulide.shiftcal.logic.io.object.JSONEmployment;
import de.nulide.shiftcal.logic.io.object.JSONSettings;
import de.nulide.shiftcal.logic.io.object.JSONShift;
import de.nulide.shiftcal.logic.io.object.JSONShiftCalendar;
import de.nulide.shiftcal.logic.io.object.JSONShiftTime;
import de.nulide.shiftcal.logic.io.object.JSONWorkDay;
import de.nulide.shiftcal.logic.object.CalendarDate;
import de.nulide.shiftcal.logic.object.Employer;
import de.nulide.shiftcal.logic.object.Employment;
import de.nulide.shiftcal.logic.object.Settings;
import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.ShiftTime;
import de.nulide.shiftcal.logic.object.WorkDay;

public class JSONFactory {

    public static JSONShiftCalendar convertShiftCalendarToJSON(ShiftCalendar sc) {
        JSONShiftCalendar ssc = new JSONShiftCalendar();
        for (int i = 0; i < sc.getShiftsSize(); i++) {
            ssc.getShifts().add(convertShiftToJSON(sc.getShiftByIndex(i)));
        }
        for (int i = 0; i < sc.getCalendarSize(); i++) {
            ssc.getCalendar().add(convertWorkDayToJSON(sc.getWdayByIndex(i)));
        }
        return ssc;
    }

    public static ShiftCalendar convertJSONToShiftCalendar(JSONShiftCalendar ssc) {
        ShiftCalendar sc = new ShiftCalendar();
        for (int i = 0; i < ssc.getShifts().size(); i++) {
            sc.addShift(convertJSONToShift(ssc.getShifts().get(i)));
        }
        for (int i = 0; i < ssc.getCalendar().size(); i++) {
            sc.addWday(convertJSONToWorkDay(ssc.getCalendar().get(i)));
        }
        return sc;
    }

    public static JSONEmployment convertEmploymentToJSON(Employment em) {
        JSONEmployment jem = new JSONEmployment();
        for (int i = 0; i < em.getEmployersSize(); i++) {
            jem.getEmployers().add(convertEmployerToJSON(em.getEmployerByIndex(i)));
        }
        return jem;
    }

    public static Employment convertJSONToEmployment(JSONEmployment jem) {
        Employment em = new Employment();
        for (int i = 0; i < jem.getEmployers().size(); i++) {
            em.addEmployer(convertJSONToEmployer(jem.getEmployers().get(i)));
        }
        return em;
    }

    public static JSONCalendarDate convertCalendarDateToJSON(CalendarDate cd) {
        return new JSONCalendarDate(cd.getYear(), cd.getMonth(), cd.getDay());
    }

    public static CalendarDate convertJSONToCalendarDate(JSONCalendarDate scd) {
        return new CalendarDate(scd.getYear(), scd.getMonth(), scd.getDay());
    }

    public static JSONShiftTime convertShiftTimeToJSON(ShiftTime st) {
        return new JSONShiftTime(st.getHour(), st.getMinute());
    }

    public static ShiftTime convertJSONToShiftTime(JSONShiftTime sst) {
        return new ShiftTime(sst.getHour(), sst.getMinute());
    }

    public static JSONShift convertShiftToJSON(Shift s) {
        return new JSONShift(s.getName(), s.getShort_name(), s.getEmployerIndex(), s.getId(),
                convertShiftTimeToJSON(s.getStartTime()), convertShiftTimeToJSON(s.getEndTime()),
                s.getColor(), s.isToAlarm(), s.isArchieved());
    }

    public static Shift convertJSONToShift(JSONShift ss) {
        return new Shift(ss.getName(), ss.getShort_name(), ss.getEmployerIndex(), ss.getId(),
                convertJSONToShiftTime(ss.getStartTime()), convertJSONToShiftTime(ss.getEndTime()),
                ss.getColor(), ss.isToAlarm(), ss.isArchieved());
    }

    public static JSONEmployer convertEmployerToJSON(Employer e) {
        return new JSONEmployer(e.getName(), e.getId(), e.isArchived());
    }

    public static Employer convertJSONToEmployer(JSONEmployer je) {
        return new Employer(je.getName(), je.getId(), je.isArchived());
    }

    public static JSONWorkDay convertWorkDayToJSON(WorkDay wd) {
        return new JSONWorkDay(convertCalendarDateToJSON(wd.getDate()), wd.getShift());
    }

    public static WorkDay convertJSONToWorkDay(JSONWorkDay swd) {
        return new WorkDay(convertJSONToCalendarDate(swd.getDate()), swd.getShift());
    }

    public static JSONSettings convertSettingsToJSON(Settings s) {
        JSONSettings js = new JSONSettings();
        js.getSettings().putAll(s.getMap());
        return js;
    }

    public static Settings convertJSONToSettings(JSONSettings js) {
        Settings s = new Settings();
        s.getMap().putAll(js.getSettings());
        return s;
    }

}
