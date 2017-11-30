package link.fitbody.link.fitbody.core;


public class Health {

    public double calculateIdealWeight(String gender, double heightCm){
        double idealWeight = -1;
        double correction = heightCm - 152;

        if(gender.equals("M")){
            idealWeight = 48;
            if(correction > 0){
                idealWeight += (correction * 1.1);
            }

        }
        else if(gender.equals("F")){
            idealWeight = 45.5;
            if(correction > 0){
                idealWeight += (correction) * 0.9;
            }
        }
        return idealWeight;
    }


    public String calculateBodyType(double bustCm, double waistCm, double hipCm){
        String shape = "";
        double  bustWaistRatio = bustCm/waistCm;
        double hipWaistRatio = hipCm/waistCm;

        if(bustWaistRatio < 1.4 && hipWaistRatio < 1.4){
            shape = "Banana";
        }
        else if(bustWaistRatio - hipWaistRatio >= 0.2){
            shape = "Apple";

        }
        else if(hipWaistRatio - bustWaistRatio >= 0.2){
            shape = "Pear";
        }
        else {
            shape = "Hourglass";
        }
        return shape;
    }


    public int calculateCalorie(String gender, int age, double heightCm, double weightKg, double activity){
        double calorieResult = -1;
        int correction = 0;
        if(gender.equals("M")){
            correction = 5;

        }
        else if(gender.equals("F")){
            correction = -161;

        }
        calorieResult = (10* weightKg + 6.25 * heightCm - 5 * age + correction) * activity;
        return (int) calorieResult; 
    }

}
