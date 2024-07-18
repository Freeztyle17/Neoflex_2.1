import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class AthletesAnalytics { 

    public static void main(String[] args) { 
    
       SparkSession spark = SparkSession.builder()
       			.appName("Athletes Analysis")
       			.master("local[*]")
       			.getOrCreate();
       String inputFile = "Athletes.csv";
       
       Dataset<Row> df = spark.read()
       		.option("header", true)
       		.option("delimiter", ";")
       		.csv(inputFile);
       		
       Dataset<Row> disciplinesCounts = df.groupBy("Discipline")
       		.count()
       		.orderBy("Discipline");
       
       System.out.println("Count of athletes by discipline:");
       disciplinesCounts.show();
       
       String outputFile = "athletes_by_discipline.parquet";
       disciplinesCounts.write()
       		.mode("overwrite")
       		.parquet(outputFile);
       
       spark.stop();
       
    } 
}
