import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class GenerateAndSaveDataFrame { 

    public static void main(String[] args) { 
    
       SparkSession spark = SparkSession.builder()
       			.appName("Olympics Disciplines")
       			.master("local[*]")
       			.getOrCreate();
       String[] disciplines = {
       		"Athletics", "Swimming", "Cycling", "Gymnastics", "Football",
       		"Skiing", "Ice hockey", "Curling", "Figure skating", "Biathlon"
       };
       String[] seasons = {
       		"Summer", "Summer", "Summer", "Summer", "Summer",
       		"Winter", "Winter", "Winter", "Winter", "Winter"
       };
       
       Dataset<Row> df = spark.createDataFrame(
       		java.util.stream.IntStream.range(0, disciplines.length)
       			.mapToObj(i -> new RowData(i, disciplines[i], seasons[i]))
       			.collect(java.util.stream.Collectors.toList()),
       	RowData.class
       );
       
       df.select("row_id", "discipline", "season")
       		.write()
       		.option("header", true)
       		.option("delimiter", "\t")
      		.csv("olympic_disciplines.csv");
       
       spark.stop();
       
    } 
}
