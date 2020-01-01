package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import properties.Config;

public class StatsModel implements java.io.Serializable {

    private static final long serialVersionUID = 9032048017082982641L;

    private static final String statsFilePath = Config.statsFolder + File.separator + Config.statsFileName;

    private Map<String, WordStats> wordsStats;

    private StatsModel() {
        this.wordsStats = new HashMap<>();
    }

    public static Object[][] getStats() {
        StatsModel statsModel = StatsModel.deserialize();
        Object[][] data = new Object[statsModel.wordsStats.size()][5];
        int row = 0;
        for (Map.Entry<String, WordStats> entry : statsModel.wordsStats.entrySet()) {
            data[row][0] = entry.getKey();
            data[row][1] = entry.getValue().getRecentAnswersCount();
            data[row][2] = entry.getValue().getRecentAccuracy();
            data[row][3] = entry.getValue().getOverallAnswersCount();
            data[row][4] = entry.getValue().getOverallAccuracy();
            ++row;
        }
        return data;
    }

    public static void recordNewAnswer(String word, boolean isCorrectAnswer) {
        StatsModel statsModel = StatsModel.deserialize();
        WordStats wordStats = statsModel.wordsStats.get(word);
        if (wordStats == null) {
            wordStats = new WordStats();
            statsModel.wordsStats.put(word, wordStats);
        }
        wordStats.recordNewAnswer(isCorrectAnswer);
        StatsModel.serialize(statsModel);
    }

    public static void deleteStatsForWord(String word) {
        StatsModel statsModel = StatsModel.deserialize();
        statsModel.wordsStats.remove(word);
        StatsModel.serialize(statsModel);
    }

    public static void clearRecentStats() {
        StatsModel statsModel = StatsModel.deserialize();
        for (Map.Entry<String, WordStats> entry : statsModel.wordsStats.entrySet()) {
            entry.getValue().clearRecentStats();
        }
        StatsModel.serialize(statsModel);
    }

    public static void deleteStats() {
        new File(statsFilePath).delete();
    }

    public static void serialize(StatsModel statsModel) {
        try {
            FileOutputStream fileOut = new FileOutputStream(statsFilePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(statsModel);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static StatsModel deserialize() {
        try {
            if (!new File(statsFilePath).exists()) {
                return new StatsModel();
            }
            FileInputStream fileInputStream = new FileInputStream(statsFilePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            StatsModel statsModel = (StatsModel) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return statsModel;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return new StatsModel();
    }
}
