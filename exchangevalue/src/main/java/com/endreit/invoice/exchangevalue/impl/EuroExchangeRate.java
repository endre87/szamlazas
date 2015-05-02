package com.endreit.invoice.exchangevalue.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Attila Biro on 24/03/2015.
 */
public class EuroExchangeRate
{
    private static final Logger LOGGER = Logger.getLogger(EuroExchangeRate.class.getName());

    private static final String URL_DATE_PATTERN = "yyyy/MM/dd";
    private static final String URL_VARIABLE_PART = "${DATE}";
    private static final String URL_PATTERN = "http://www.infovalutar.ro/" + URL_VARIABLE_PART + "/eur.bnr";

    private static final EuroExchangeRate INSTANCE = new EuroExchangeRate();

    public static EuroExchangeRate getInstance()
    {
        return INSTANCE;
    }

    private EuroExchangeRate()
    {
    }

    private String getUrlPath(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(URL_DATE_PATTERN);
        String stringDate = dateFormat.format(date);
        return URL_PATTERN.replace(URL_VARIABLE_PART, stringDate);
    }

    public float getExchangeRate(Date date) throws Exception
    {
        LOGGER.info(String.format("Getting euro exchange rate for date: %s ...", date));

        String urlPath = getUrlPath(date);
        URL url = new URL(urlPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String strTemp = br.readLine();

        LOGGER.info(String.format("Exchange rate %s 1 Euro = %s RON", date, strTemp));

        return Float.valueOf(strTemp);
    }
}
