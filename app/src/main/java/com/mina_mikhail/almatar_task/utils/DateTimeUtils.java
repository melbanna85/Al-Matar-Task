/*
 * *
 *  * Created by MIna Mikhail on 3/18/19 12:27 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 3/18/19 12:11 AM
 *
 */

package com.mina_mikhail.almatar_task.utils;

import android.text.format.DateUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class DateTimeUtils {

  public static String trimDateTime(String inputFormat, String outputFormat, String dateTime) {
    DateFormat inputFormatter = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
    Date date = null;
    try {
      date = inputFormatter.parse(dateTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    DateFormat outputFormatter = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

    if (date == null) {
      return null;
    } else {
      return outputFormatter.format(date);
    }
  }

  public static String getCurrentDataTime() {
    DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    String date = dfDate.format(Calendar.getInstance().getTime());
    DateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    String time = dfTime.format(Calendar.getInstance().getTime());
    return date + " " + time;
  }

  public static String getCurrentDate() {
    DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    return dfDate.format(Calendar.getInstance().getTime());
  }

  public static String getCurrentTime() {
    DateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    return dfTime.format(Calendar.getInstance().getTime());
  }

  public static String convert24HrTo12Hr(int hourOfDay, int minute) {
    if (String.valueOf(Locale.getDefault().getLanguage()).equals("en")) {
      return ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute)
          : minute) + " " +
          ((hourOfDay >= 12) ? "PM" : "AM");
    } else if (String.valueOf(Locale.getDefault().getLanguage()).equals("ar")) {
      return ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute)
          : minute) + " " +
          ((hourOfDay >= 12) ? "م" : "ص");
    }

    return null;
  }

  public static String changeDateFormat(String date, String oldFormat, String newFormat) {
    SimpleDateFormat formatView = new SimpleDateFormat(oldFormat);
    SimpleDateFormat newFormatView = new SimpleDateFormat(newFormat);
    Date dateObj = null;
    try {
      dateObj = formatView.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return newFormatView.format(dateObj);
  }

  public static String convertDateTimeToTimesAgo(String timeStamp, String timeFormat) {
    // To convert date from style like (2017-09-01 21:35:40) into -> times ago style
    final SimpleDateFormat inputFormat =
        new SimpleDateFormat(timeFormat, Locale.ENGLISH);
    Date date;
    try {
      date = inputFormat.parse(timeStamp);

      // the new date style
      return (String) DateUtils
          .getRelativeTimeSpanString(date.getTime(), Calendar.getInstance().getTimeInMillis(),
              DateUtils.MINUTE_IN_MILLIS);
    } catch (Exception e) {
      e.printStackTrace();

      return "";
    }
  }

  public static int getDifferenceBetweenTwoTimesInHours(String timeFormat, String firstTime,
      String secondTime) {
    SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.ENGLISH);
    try {
      Date firstDate;
      Date secondDate;
      firstDate = sdf.parse(firstTime);
      secondDate = sdf.parse(secondTime);

      long diffInMilli = Math.abs(secondDate.getTime() - firstDate.getTime());
      long diffInHours = TimeUnit.HOURS.convert(diffInMilli, TimeUnit.MILLISECONDS);

      return (int) diffInHours;
    } catch (ParseException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public static int getCurrentTimeInt() {
    return (int) (new Date().getTime() / 1000);
  }
}
