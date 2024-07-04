import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static double userInput(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("This program works out the top speed of a vehicle with a given mass and power output.");
        double mass = userInput("Mass (kg): ");

        double inputPower = userInput("Power (W): ");

        double airDensity = 1.225; // 15C at sea level
        double dragCoefficient = 0.3; // normal car
        double frontalArea = 0.85 * 1.89 * 1.46; // normal car

        double velocity = 0.1;
        double deltaTime = 0.00001;

        double accelerationThreshold = 0.1;
        double acceleration;
        double time = 0.0;
        int latestTime = 0;

        do
        {
            double airResistanceForce = ((airDensity * dragCoefficient * frontalArea) / 2.0) * velocity * velocity;
            double power = inputPower - (airResistanceForce * velocity);
            double velocityAsKineticEnergy = 0.5 * mass * velocity * velocity;
            double kineticEnergy = velocityAsKineticEnergy + (power * deltaTime);
            double newVelocity = Math.sqrt((2 * kineticEnergy) / mass);
            acceleration = (newVelocity - velocity) / deltaTime;
            velocity = newVelocity;

            boolean newTimeDifferent = (int)time != latestTime;
            if (newTimeDifferent)
            {
                latestTime = (int)time;
                Thread.sleep(1000);
                System.out.printf("Time: %d seconds, Velocity: %f m/s.%n", latestTime, velocity);
            }

            time += deltaTime;
        } while (acceleration > accelerationThreshold);
    }
}