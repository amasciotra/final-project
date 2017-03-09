package finalproject;

import java.util.Map;

import finalproject.objects.GameData;
import finalproject.utilities.LCDInfo;
import finalproject.utilities.Navigation;
import finalproject.utilities.Odometer;
import finalproject.utilities.WifiConnection;
import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.utility.Delay;

@SuppressWarnings("rawtypes")
/**
 * This is the main class that will execute all functions of the robot.
 * @author maxsn
 *
 */
public class MainController {
	private static final String SERVER_IP = "192.168.2.19";
	private static final int TEAM_NUMBER = 7;

	private static final boolean ENABLE_DEBUG_WIFI_PRINT = false;
	
	private static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));
	private static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	
	private static final TextLCD t = LocalEV3.get().getTextLCD();
	
	private static Odometer odometer;
	private static LCDInfo lcdInfo;
	private static Navigation navigation;
		
	public static void main(String[] args) {
		
		// Print message on the LCD screen
		t.clear();
		t.drawString("                ", 0, 0);
		t.drawString("   - TEAM 7 -   ", 0, 1);
		t.drawString("                ", 0, 2);
		t.drawString(" Press ENTER to ", 0, 3);
		t.drawString("      start     ", 0, 4);
		
		// Wait for user input before starting
		while (Button.waitForAnyPress() != Button.ID_ESCAPE) {
			Delay.msDelay(50);
		}
		
		// Instantiate utilities
		odometer = new Odometer(leftMotor, rightMotor, 30, true);
		lcdInfo = new LCDInfo(odometer);
		navigation = new Navigation(odometer);
		
		// 1. Get game data from Wi-Fi
/*		WifiConnection wc = new WifiConnection(SERVER_IP, TEAM_NUMBER, ENABLE_DEBUG_WIFI_PRINT);
		Map data;
		GameData gd;
		
		try {
			data = wc.getData();
			gd = new GameData(data, TEAM_NUMBER);
		} catch (Exception e) {
			t.clear();
			System.out.println(e.getMessage());
			return;
		}
		
		t.clear();
		// 1. complete.
		
		// Below lines are for debug
		System.out.println("Role: " + gd.getRole());
		System.out.println("Corner: " + gd.getStartingCorner());
		System.out.println("Omega: " + gd.getOmega());
		System.out.println("FWD line: " + gd.getForwardLine());
		System.out.println("Press ENTER...");
		
		Button.waitForAnyPress();
		
		System.out.println("DspnsrX: " + gd.getDispenserPosition().getX());
		System.out.println("DspnsrY: " + gd.getDispenserPosition().getY());
		System.out.println();
		System.out.println("DefzonX: " + gd.getDefenderZone().getX());
		System.out.println("DefzonY: " + gd.getDefenderZone().getY());
		
		wc = null;
*/
		
		/* 
		 * March 9, unable to get WifiConnection working 
		 * JSON Object not found
		 * Tested without WifiConnection
		 */
		odometer.driveSquare();
		
		Button.waitForAnyPress();
		System.exit(0);
		
	}

}
