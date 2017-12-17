package com.CasinoGames.GameLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

public class CLogger
{
    public String fileName = "C:\\Users\\mcasey\\CasinoGames\\src\\CasinoLog.txt";
    public File logFile = new File(fileName);

    public String getDateTime()
    {
        Date date = new Date();
        return date.toString();
    }

    private void writeLog(String message)
    {
        FileWriter fileWriter = null;
        try
        {
            if (logFile.exists() == false)
            {
                logFile.createNewFile();
                System.out.println("Created new file "+fileName);
            }
        } catch (IOException e)
        {
            System.out.println(e.getClass().getSimpleName()+" - "+e.getMessage());
        }

        try
        {

            fileWriter = new FileWriter(logFile, true);

            fileWriter.write(message+"\n");
        } catch (IOException e)
        {
            System.out.println(e.getClass().getSimpleName()+" - "+e.getMessage());
        } finally
        {
            try
            {
                if(fileWriter != null)
                {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e)
            {
                System.out.println(e.getClass().getSimpleName()+" - "+e.getMessage());
            }
        }
    }

    public void log(Level level, String cls, String method, String message)
    {
        String formattedLogMessage = getDateTime()+"  "+level.getLocalizedName()+"  [C:"+cls.toString()+"]  {M:"+method+"()}  ->   "+message;
        writeLog(formattedLogMessage);
    }
}
