import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SensorDataProcessor {
// Sensor data and limits.

    public double[][][] sensorData;// Three-dimensional array to store sensor data
    public double[][] limits;// Two-dimensional array to store limits
// constructor

    public SensorDataProcessor(double[][][] sensorData, double[][] limits) {
        this.sensorData = sensorData;
        this.limits = limits;
    }
// Calculates the average of an array

    private double calculateAverage(double[] array) {
        int index= 0;
        double sum = 0;
        for (index = 0; index < array.length; index++) {
            sum += array[index];
        }
        return sum / array.length;
    }
// Performs data calculation and writes the result to a file

     public void calculate(double divisor) {
        double[][][] calculatedData = new double[sensorData.length][sensorData[0].length][data[0][0].length];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RacingStatsData.txt"))) {
            for (int i = 0; i < sensorData.length; i++) {
                for (int j = 0; j < sensorData[0].length; j++) {
                    for (int k = 0; k < sensorData[0][0].length; k++) {
                        // Calculate the processed data value
                        double divisor = 0.0; // Define the divisor variable
                         calculatedData[i][j][k] = sensorData[i][j][k] / divisor - Math.pow(limits[i][j], 2.0);
                        
                         // Calculate the average of the current sensor data
                        double currentAverage = calculateAverage(calculatedData[i][j]);
                          // Check conditions for further processing
                        if (currentAverage > 10 && currentAverage  < 50) {
                             // Stop processing this particular sensor data
                            break;
                        } else if (Math.max(sensorData[i][j][k], calculatedData[i][j][k]) > sensorData[i][j][k]) {
                              // Stop processing this particular sensor data
                            break;
                        } else if (Math.pow(Math.abs(sensorData[i][j][k]), 3) < Math.pow(Math.abs(calculatedData[i][j][k]), 3)
                                && calculateAverage(sensorData[i][j]) < calculatedData[i][j][k] && (i + 1) * (j + 1) > 0) {
                             // Multiply the calculatedData value by 2
                            calculatedData[i][j][k] *= 2;
                        }
                    }
                }
            }
              // Write calculated data to the file
            for (double[][] layer : calculatedData) {
                for (double[] row : layer) {
                    for (double value : row) {
                        writer.write(Double.toString(value));
                        writer.newLine();
                    }
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
