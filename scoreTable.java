public class scoreTable{

  public static void updateTable(int scoreOutput){

    int rank1=0;
    int rank2=0;
    int rank3=0;
    int rank4=0;
    int rank5=0;


    if(scoreOutput>rank1)
        rank1=scoreOutput;
      else if(scoreOutput>rank2)
        rank2=scoreOutput;
      else if(scoreOutput>rank3)
        rank3=scoreOutput;
      else if(scoreOutput>rank4)
        rank4=scoreOutput;
      else if(scoreOutput>rank5)
        rank5=scoreOutput;

  }

  public static void main(String[] args) {

  }

}
