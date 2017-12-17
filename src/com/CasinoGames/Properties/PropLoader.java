package com.CasinoGames.Properties;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropLoader
{
    private static boolean allowSplitting;
    private static boolean allowDoubleDown;
    private static boolean allowNegativeChipCount;
    private static int negativeChipCountAmount;

    // FOR PROPERTIES FILE
    private static boolean splitting;
    private static boolean doubleDown;
    private static boolean negativeChips;


    public static boolean isAllowSplitting()
    {
        return allowSplitting;
    }

    public static void setAllowSplitting(boolean allowSplitting)
    {
        PropLoader.allowSplitting = allowSplitting;
    }

    public static boolean isAllowDoubleDown()
    {
        return allowDoubleDown;
    }

    public static void setAllowDoubleDown(boolean allowDoubleDown)
    {
        PropLoader.allowDoubleDown = allowDoubleDown;
    }

    public static boolean isAllowNegativeChipCount()
    {
        return allowNegativeChipCount;
    }

    public static void setAllowNegativeChipCount(boolean allowNegativeChipCount)
    {
        PropLoader.allowNegativeChipCount = allowNegativeChipCount;
    }

    public static int getNegativeChipCountAmount()
    {
        return negativeChipCountAmount;
    }

    public static void setNegativeChipCountAmount(int negativeChipCountAmount)
    {
        PropLoader.negativeChipCountAmount = negativeChipCountAmount;
    }


    // FOR PROPERTIES FILE
    public static boolean isSplitting() {
        return splitting;
    }

    public static void setSplitting(boolean splitting) {
        PropLoader.splitting = splitting;
    }

    public static boolean isDoubleDown() {
        return doubleDown;
    }

    public static void setDoubleDown(boolean doubleDown) {
        PropLoader.doubleDown = doubleDown;
    }

    public static boolean isNegativeChips() {
        return negativeChips;
    }

    public static void setNegativeChips(boolean negativeChips) {
        PropLoader.negativeChips = negativeChips;
    }

    /*Grabs key value properties from xml file and loads them*/
    public static void loadProperties() throws IOException
    {
        Properties properties = new Properties();
        try
        {
            Properties defaultProps = new Properties();
            try(InputStream inputStream = PropLoader.class.getResourceAsStream("blackjackprop.xml"))
            {
                defaultProps.loadFromXML(inputStream);
            }

            Path propFile = Paths.get("blackjackprop.xml");
            if(Files.exists(propFile))
            {
                try(InputStream inputStream = Files.newInputStream(propFile))
                {
                    properties.loadFromXML(inputStream);
                }
            }

            setAllowSplitting(Boolean.parseBoolean(defaultProps.getProperty("allowSplitting")));
            setAllowDoubleDown(Boolean.parseBoolean(defaultProps.getProperty("allowDoubleDown")));
            setAllowNegativeChipCount(Boolean.parseBoolean(defaultProps.getProperty("allowNegativeChipCount")));
            setNegativeChipCountAmount(Integer.parseInt(defaultProps.getProperty("negativeChipCountAmount")));

            /*System.out.println(isAllowSplitting());
            System.out.println(isAllowDoubleDown());
            System.out.println(isAllowNegativeChipCount());
            System.out.println(getNegativeChipCountAmount());*/
        }catch(IOException e)
        {
            System.out.println("Exception <"+e.getClass().getSimpleName()+"> "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadPropertiesFile() throws IOException
    {
        Properties properties = new Properties();
        try(Reader reader = Files.newBufferedReader(Paths.get("blackjackprop.properties")))
        {
            properties.load(reader);
        }
        setSplitting(Boolean.parseBoolean(properties.getProperty("splitting")));
        setDoubleDown(Boolean.parseBoolean(properties.getProperty("doubledown")));
        setNegativeChips(Boolean.parseBoolean(properties.getProperty("negativeChips")));

        /*System.out.println(splitProp);
        System.out.println(doubleDownProp);
        System.out.println(negativeChipsProp);*/
    }

}
