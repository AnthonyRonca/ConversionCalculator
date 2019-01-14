package sample;

import java.util.Arrays;
import java.lang.Math;
    public class Part1
    {
        //String array that lists the units from smallest to largest
        public static final String[] UNITS = {"TSP","TBSP","OZ","CUP","PT","QT","GAL"};

        //Conversion Ratios to move units up or down to next size in list
        private static final double TSPUP = 1.0/3.0;
        private static final double TBSPDOWN = 3.0;
        private static final double TBSPUP = 1.0/2.0;
        private static final double OZDOWN = 2.0;
        private static final double OZUP = 1.0/8.0;
        private static final double CUPDOWN = 8.0;
        private static final double CUPUP = 1.0/2.0;
        private static final double PTDOWN = 2.0;
        private static final double PTUP = 1.0/2.0;
        private static final double QTDOWN = 2.0;
        private static final double QTUP = 1.0/4.0;
        private static final double GALDOWN = 4.0;

        //Convert method.
        private static double convert(double input, double ratio)
        {
            return input * ratio;
        }

        //Controller method
        //gets the amount of conversions and determines if the conversions are up or down
        public static double conversionController(double startValue, String startUnit, String endUnit)
        {
            //get index of the start and stop units (Will return -1 if its not in the array)
            int startUnitIndex = Arrays.asList(UNITS).indexOf(startUnit);
            int endUnitIndex = Arrays.asList(UNITS).indexOf(endUnit);
            //determine how many conversions are needed to reach desired units
            int convertsNeeded = Math.abs(startUnitIndex-endUnitIndex);
            //Set the index and value of the current unit/value these will update with each conversion
            int currentUnitIndex = startUnitIndex;
            double currentValue = startValue;
            //check if the units are the same, if units are the same do nothing further and return the startValue
            if(startUnitIndex == endUnitIndex)
            {
                //check if either of the units is outside the list (Should not happen) If units are outside of the list then return a 0.0
            }else if(startUnitIndex == -1 || endUnitIndex == -1)
            {
                currentValue = 0.0;
                //next check if the start unit is smaller than the end unit
                //if so then convert the unit as many times as needed to get to the end
            }else if(startUnitIndex < endUnitIndex)
            {
                //keep converting until convertsNeeded is 0
                while (convertsNeeded > 0)
                {
                    //check the current unit's index to determine which "UP" conversion is needed
                    if(currentUnitIndex == 0)
                        currentValue = convert(currentValue,TSPUP);
                    else if(currentUnitIndex == 1)
                        currentValue = convert(currentValue,TBSPUP);
                    else if(currentUnitIndex == 2)
                        currentValue = convert(currentValue,OZUP);
                    else if(currentUnitIndex == 3)
                        currentValue = convert(currentValue,CUPUP);
                    else if(currentUnitIndex == 4)
                        currentValue = convert(currentValue,PTUP);
                    else
                        currentValue = convert(currentValue,QTUP);
                    //increment the currentUnit's index and decrement the converts needed
                    currentUnitIndex++;
                    convertsNeeded--;
                }
                //if the start unit is larger than the end unit then convert down as many times as needed
            }else
            {
                //keep converting until convertsNeeded is 0
                while (convertsNeeded > 0)
                {
                    //check the current unit's index to determine which "DOWN" conversion is needed
                    if(currentUnitIndex == 1)
                        currentValue = convert(currentValue,TBSPDOWN);
                    else if(currentUnitIndex == 2)
                        currentValue = convert(currentValue,OZDOWN);
                    else if(currentUnitIndex == 3)
                        currentValue = convert(currentValue,CUPDOWN);
                    else if(currentUnitIndex == 4)
                        currentValue = convert(currentValue,PTDOWN);
                    else if(currentUnitIndex == 5)
                        currentValue = convert(currentValue,QTDOWN);
                    else
                        currentValue = convert(currentValue,GALDOWN);
                    //decrement the currentUnitsIndex and decrement the convertsNeeded
                    currentUnitIndex--;
                    convertsNeeded--;
                }
            }
            //return the double of the unit's value
            return currentValue;
        }
    }

