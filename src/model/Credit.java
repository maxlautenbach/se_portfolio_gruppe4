package model;

public class Credit{

    private float loanAmount;
    private float interestRate;
    private String interestPeriod;
    private int period;
    private String periodUoM;
    private float repaymentAmount;
    private String repaymentPeriod;
    private String creditType;
    private float endAmount;


    public Credit(float loanAmount, float interestRate, String interestPeriod, int period, String periodUoM, float repaymentAmount, String repaymentPeriod, String creditType) {
        this.loanAmount = loanAmount;
        this.endAmount = loanAmount;
        this.interestRate = interestRate;
        this.interestPeriod = interestPeriod;
        this.period = period;
        this.periodUoM = periodUoM;
        this.repaymentAmount = repaymentAmount;
        this.repaymentPeriod = repaymentPeriod;
        this.creditType = creditType;
    }

    public float calculateEndAmount(){
        int interestPeriod = 0;
        int repaymentPeriod = 0;
        int period = 0;
        switch (this.interestPeriod){
            case "monthly":
                if(this.repaymentPeriod.equals("yearly")){
                    this.repaymentPeriod = "monthly";
                    this.repaymentAmount /= 12.0;
                }
                interestPeriod = 1;
                break;
            case "yearly":
                interestPeriod = 12;
                break;
        }
        switch (this.repaymentPeriod){
            case "monthly":
                if(interestPeriod == 12){
                    interestPeriod = 1;
                    float rate =  Math.round(Math.pow(this.interestRate, 1.0 / 12.0));
                }
                repaymentPeriod = 1;
                break;
            case "yearly":
                repaymentPeriod = 12;
                break;
        }
        switch (this.periodUoM){
            case "monthly":
                period = this.period;
                break;
            case "yearly":
                period = this.period * 12;
                break;
        }

        for(int i = 1; i <= period; i++){
            if(i % interestPeriod == 0){
                this.endAmount += (this.loanAmount * interestRate);
            }
            if(i % repaymentPeriod == 0){
                this.loanAmount -= repaymentAmount;
            }
        }

        return this.endAmount;
    }

}