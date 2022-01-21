import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
class Tief {
    int stolenCabritos = 0;
    int stolenFarmCount = 0;
    Farm farm = new Farm();

    public int getNextPosition(Farm actualFarm){
        if(actualFarm.cabritos % 2 == 0){
            return actualFarm.position - 1;
        }else {
            return actualFarm.position + 1;
        }
    }
    public Farm getNextFarm(Farm[] farms, int nextPosition){
        Farm nextFarm = null;
        for (Farm value : farms) {
            if (value.position == nextPosition) {
                nextFarm = value;
            }
        }
        return nextFarm;
    }

    public void stealFarm(Farm farm){
        if(farm != null){
            if(farm.cabritos >= 1) {
                farm.cabritos -= 1;
                this.stolenCabritos += 1;
                this.stolenFarmCount += 1;
            }
        }

    }

    public void startMadness(Farm[] farms){
        int nextPosition = getNextPosition(this.farm);
        Farm nextFarm = getNextFarm(farms, nextPosition);

        this.farm.cabritos -= 1;
        this.stolenCabritos += 1;
        this.stolenFarmCount += 1;

        while(nextFarm != null){
            nextPosition = getNextPosition(nextFarm);
            stealFarm(nextFarm);
            nextFarm = getNextFarm(farms, nextPosition);

        }

    }
}
class Farm {
    int cabritos;
    int position;

    public int steelCabrito() {
        this.cabritos -= 1;
        return this.cabritos;
    }
    @Override
    public String toString() {
        return " position: " + position + " cabritos: " + cabritos;
    }
}
public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String farmsQuantity = scanner.nextLine();
        Random rand = new Random();
        Farm[] farms;
        farms = new Farm[8];
        for(int i = 0; i < Integer.parseInt(farmsQuantity); i++) {
            Farm farm = new Farm();
            farm.cabritos =  scanner.nextInt();
            farm.position = i;
            farms[i] = farm;
        }



        Tief tief = new Tief();
        tief.farm = farms[0];

        Farm[] cloneOfFarms;
        cloneOfFarms = new Farm[farms.length];
        for(int i = 0; i < farms.length; i++){
            Farm newFarm = new Farm();
            newFarm.position = farms[i].position;
            newFarm.cabritos = farms[i].cabritos;
            cloneOfFarms[i] = newFarm;

        }
        tief.startMadness(farms);

        int totalCabritosLeftSteal = 0;
        int totalFarmsSteal = 0;

        for(int i = 0; i < farms.length; i++){
            totalCabritosLeftSteal += farms[i].cabritos;
            if(farms[i].cabritos != cloneOfFarms[i].cabritos){
                totalFarmsSteal += 1;
            }
        }

        System.out.println((totalFarmsSteal) +  " " + (totalCabritosLeftSteal));

    }
}
