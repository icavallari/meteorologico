package br.com.tcc.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Check {

    public static Boolean naoNulo(Object o) {

        if (o instanceof Collection<?>) {
            return ((Collection<?>) o) != null || ((Collection<?>) o).size() != 0;
        }

        if (o instanceof String) {
            String string = (String) o;
            if (string != null && !string.trim().isEmpty()) {
                return true;
            }
            return false;
        }

        return o != null;
    }

    /**
     * compara datas ignorando os segundos
     */
    public static Boolean mesmaData(Date d1, Date d2) {
        return getDataSemSegundos(d1).equals(getDataSemSegundos(d2));
    }

    public static Date getDataSemSegundos(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
