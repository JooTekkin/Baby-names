import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class TasksPart {

    public void totalBirths(){
        int numsBoys = 0;
        int numsGirls = 0;
        int total = 0;
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser(false);
        for(CSVRecord record : parser){
            if(record.get(1).contains("F")){
                numsGirls++;
            }else {
                numsBoys++;
            }
        }
        total = numsBoys + numsGirls;
        System.out.println("Numbers of boys = " + numsBoys);
        System.out.println("Numbers of girls = " + numsGirls);
        System.out.println("Total = " + total);
    }


    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser(false);
        for (CSVRecord record : parser){
            if(record.get(1).contains(gender)){
                rank++;
                if (record.get(0).contains(name)){
                    return rank;
                }
            }
        }

        return -1;
    }



    public String getName(int year, int rank, String gender){
        int currRank = 0;

        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser(false);
        for(CSVRecord record : parser){
            if(record.get(1).contains(gender)){
                currRank++;
                if(currRank == rank){
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }


    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String newName = null;
        int rankOldName = getRank(year,name,gender);
        newName = getName(year,rankOldName,gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }



    public int yearOfHighestRank(String name, String gender){
        int rank = 0;
        int year = -1;
      DirectoryResource dr = new DirectoryResource();
      for (File file : dr.selectedFiles()){
          FileResource fileResource = new FileResource(file);
          CSVParser parser = fileResource.getCSVParser(false);
          int currRank = getRank(parser,name,gender);
          if(currRank < rank && currRank != -1){
              rank = currRank;
              year = Integer.parseInt(file.getName().substring(3,7));
          } else if (rank == 0 && currRank != -1) {
              rank = currRank;
              year = Integer.parseInt(file.getName().substring(3,7));
          }
      }
        return year;
    }



    private int getRank(CSVParser parser, String name, String gender){
        int rank = 0;
        for (CSVRecord record : parser){
            if(record.get(1).contains(gender)){
                rank++;
                if (record.get(0).contains(name)){
                    return rank;
                }
            }
        }

        return -1;
    }


    public double getAverageRank(String name, String gender){
        double numsRank = 0.0;
        double sumRank = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            CSVParser parser = fileResource.getCSVParser(false);
            int currRank = getRank(parser, name, gender);
            if (currRank != -1){
                numsRank++;
                sumRank += currRank;
            }
        }
        return sumRank/numsRank;
    }


    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalRank = 0;
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser(false);
        for (CSVRecord record : parser){
            if (record.get(1).contains(gender)){
                if(record.get(0).contains(name)){
                    break;
                }
                totalRank += Integer.parseInt(record.get(2));
            }
        }
        return totalRank;
    }
}