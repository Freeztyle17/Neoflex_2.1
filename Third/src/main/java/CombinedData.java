import org.apache.spark.sql.*;

public class CombinedData { 

    public static void main(String[] args) { 
    
       SparkSession spark = SparkSession.builder()
       			.appName("Athletes Analysis")
       			.master("local[*]")
       			.getOrCreate();
       			
       String inputFile = "DataFrame.csv";
       
       Dataset<Row> df = spark.read()
       		.option("header", true)
       		.option("delimiter", "\t")
       		.csv(inputFile);
       		
       df.printSchema();
       df.show();
       
       Dataset<Row> athletesDF = spark.read().option("delimiter", ";").option("header", true).csv("Athletes.csv");
       
       athletesDF.show();
       
       Dataset<Row> aggregatedDF = athletesDF.groupBy("Discipline")
       		.agg(functions.countDistinct("Name").alias("participants"));
       
       aggregatedDF.printSchema();
       aggregatedDF.show();
       
       Dataset<Row> combinedDF = df.join(aggregatedDF, "discipline")
       		.select("discipline", "season","Participants");
       		
       combinedDF.show();
       
       String outputFile = "combined.parquet";
       combinedDF.write()
       		.mode("overwrite")
       		.parquet(outputFile);
       
       spark.stop();
       
    } 
}
