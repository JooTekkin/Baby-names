public class Main {
    public static void main(String[] args) {
        TasksPart tasksPart = new TasksPart();

        tasksPart.totalBirths();
        System.out.println(tasksPart.getRank(2012,"Frank","M"));
        System.out.println(tasksPart.getName(2012,450 ,"M"));
        tasksPart.whatIsNameInYear("Owen",1974,2014,"M");
        System.out.println(tasksPart.yearOfHighestRank("Mich","M"));
        System.out.println(tasksPart.getAverageRank("Susan","F"));
        System.out.println(tasksPart.getTotalBirthsRankedHigher(2012,"Ethan","M"));

    }
}