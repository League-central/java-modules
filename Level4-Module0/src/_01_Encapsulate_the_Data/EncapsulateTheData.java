package _01_Encapsulate_the_Data;

public class EncapsulateTheData {

    /*
     * itemsReceived cannot negative. All negative parameters should set
     * itemsReceived to 0.
     */

    int itemsReceived;

    /*
     * degreesTurned must be locked between 0.0 and 360.0 inclusive. All
     * parameters outside this range should set degreesTurned to the nearest
     * bound.
     */

    double degreesTurned;

    /*
     * nomenclature must not contain an empty String. An empty String parameter
     * should set nomenclature to a String with a single space.
     */

    String nomenclature;

    /*
     * memberObj must not be a String. A String parameter should set memberObj
     * to a new Object(); Hint: Use the instanceof operator.
     */

    Object memberObj;

}
