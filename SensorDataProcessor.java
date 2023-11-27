import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SensorDataProcessor {
// Senson data and limits.

    public double[][][] data;
    public double[][] limit;
// constructor

    public SensorDataProcessor(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }
// calculates average of sensor data

    private double average(double[] array) {
        int i = 0;
        double val = 0;
        for (i = 0; i < array.length; i++) {
            val += array[i];
        }
        return val / array.length;
    }
// calculate data

     public void calculate(double divisor) {
        double[][][] calculatedData = new double[data.length][data[0].length][data[0][0].length];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RacingStatsData.txt"))) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    for (int k = 0; k < data[0][0].length; k++) {
                        calculatedData[i][j][k] = data[i][j][k] / divisor - Math.pow(limit[i][j], 2.0);

                        double average = calculateAverage(calculatedData[i][j]);

                        if (average > 10 && average < 50) {
                            break;
                        } else if (Math.max(data[i][j][k], calculatedData[i][j][k]) > data[i][j][k]) {
                            break;
                        } else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(calculatedData[i][j][k]), 3)
                                && calculateAverage(data[i][j]) < calculatedData[i][j][k] && (i + 1) * (j + 1) > 0) {
                            calculatedData[i][j][k] *= 2;
                        }
                    }
                }
            }
