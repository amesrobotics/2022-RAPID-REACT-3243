package frc.robot;

import frc.robot.Constants; 

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController; 

public final class JoyUtil extends XboxController {


    /* ++ we'll use this class to write methods that help us process joystick inputs
    * and will mostly be for drive train things, and will include things like:
    * deadzone functions and joystick input curves, and anything else we need. 
    */

    // ++ WE HAVE A BUNCH OF FUNCTIONS HERE, AND WE NEED TO APPLY THEM IN THE RIGHT ORDER
    // ++ check the "composeDriveJoyFunctions" method at the bottom to see the order this should be done in 
    // ++ (((I'm not putting it here to avoid multiple versions of the "correct" order)))


    // ++ constructor
    public JoyUtil(int controllerID) {
        super(controllerID);
    }


    // ++ these are the methods used to 
    double prevFilteredX;
    double prevFilteredY;
    double prevFilteredR;

    public void zeroPreviousFiltered() {
        // ++ this zeroes all the previous filtered values so that
        prevFilteredX = 0.0;
        prevFilteredY = 0.0;
        prevFilteredR = 0.0;
    }

    

    // ++ these methods make it so you don't have to pass anything in when you call them, you just call the 
    // ++ method that corresponds with the joystick you want. It also keeps track of the previous filtered value 
    public double getDriveXWithAdjustments(){
        double rawJoyPos = getLeftX(); 
        double filterStrength = Constants.Joysticks.driveLowPassFilterStrength;
        double damperStrength = Constants.Joysticks.driveSpeedDamper;
        double adjustedPos = composeDriveJoyFunctions(rawJoyPos, prevFilteredX, filterStrength, damperStrength); 
        prevFilteredX = lowPassFilter(rawJoyPos, prevFilteredX, filterStrength);
        return adjustedPos;
    }
    public double getDriveYWithAdjustments(){
        double rawJoyPos = getLeftY(); 
        double filterStrength = Constants.Joysticks.driveLowPassFilterStrength;
        double damperStrength = Constants.Joysticks.driveSpeedDamper;
        double adjustedPos = composeDriveJoyFunctions(rawJoyPos, prevFilteredY, filterStrength, damperStrength); 
        prevFilteredY = lowPassFilter(rawJoyPos, prevFilteredY, filterStrength);
        return adjustedPos;
    }
    public double getRotationWithAdjustments() {
        // ++ this gets the position on the rotation axis, then adjusts it to what we need
        // ++ right now, we need a deadzone and then a low pass filter, but that might change later
        
        // ++ the rotation axis is right x
        double rawJoyPos = getRightX();
        double filterStrength = Constants.Joysticks.rotationLowPassFilterStrength;
        double damperStrength = Constants.Joysticks.rotationDamper;
        double adjustedPos = ( lowPassFilter( posWithDeadzone(rawJoyPos), prevFilteredR, filterStrength) * damperStrength );
        return adjustedPos;
    }





    // ++ these are the methods called above ===================================================================

    public static double posWithDeadzone(double pos) {
        // ++ takes input and compares it to deadzone size
        // returns joystick size if it's greater than the deadzone, 0 otherwise

        double deadZoneSize = SmartDashboard.getNumber("deadzone size", Constants.Joysticks.deadZoneSize);
        SmartDashboard.putNumber("deadzone size", deadZoneSize);

        if (Math.abs(pos) >= deadZoneSize ) {
            return pos;
        } else {
            return 0.0; 
        }
    }

  
    public static double lowPassFilter(double pos, double prevFilterJoy, double filterStrength) {
        // ++ this method smoothes out the joystick input so 
        // ++ "prevFilterJoy" is the previous output of this function
        double filteredSpeed = ((filterStrength * prevFilterJoy) + ((1- filterStrength) * pos));
        return filteredSpeed;
    }


    public static double joyCurve(double pos) {
        // ++ this method will take the linear joystick input and puts it into a polynomial curve

        double a = Constants.Joysticks.aCoeff; 
        double b = Constants.Joysticks.bCoeff;
        int firstPower = Constants.Joysticks.firstPower; 
        int secondPower = Constants.Joysticks.secondPower; 

        return ( (a * (Math.pow(pos,firstPower))) + (b * (Math.pow(pos,secondPower))) ); 
    }


    public double composeDriveJoyFunctions(double rawJoyPos, double prevFilterJoy, double filterStrength, double damperStrength){
        // ++ IMPORTANT: please note that this function now shouldn't be called outside of this class-- this class used to be
        // ++ just full of methods, but it's now a wrapper class

        /* ++ this method will compose all the previous joy functions, so
        * THIS WILL BE THE ONLY METHOD USED for adjusting the drive joysticks
        *
        * ++ we need to apply the methods in the RIGHT ORDER
        * we want to do:
        * - deadzone
        * - low pass filtering
        * - joy curve
        * - convert joystick range [-1, 1] to range of robot speed [-max speed, max speed]
        * - dampen the output w/ a multiplier
        * and the order might have to be changed as we add more functions, 
        * but deadzone should probably stay first, and dampening should probably stay last
        */ 

        double withDead = posWithDeadzone(rawJoyPos);
        double withFilter = lowPassFilter(withDead, prevFilterJoy, filterStrength);
        double withCurve = joyCurve(withFilter); 
        double withSpeedRange = withCurve * Constants.DriveTrain.maxWheelSpeed;
        double withDampened = withSpeedRange * damperStrength; 

        // ++ I decided to make seperate variables for everything to make it a little more readable

        return withDampened;
        // ++ we return [whatever it says was returned above] because that's the last method so far, and
        // it'll need to be changed if/when more functions are added
    }





//int max = annette;waz here;
//lol hiiiiiiiiiiiiiiiiiiii
// hi max :)
// good job doing important things
//go u
// bbbbbbbbbbbbbb


}
