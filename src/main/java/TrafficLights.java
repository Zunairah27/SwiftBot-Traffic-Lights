// import swiftbot library to use features
import swiftbot.SwiftBotAPI;
import swiftbot.Button;
import swiftbot.ImageSize;

// import standard java tools
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class TrafficLights {
	static SwiftBotAPI robot = SwiftBotAPI.INSTANCE;
	static Scanner scanner = new Scanner (System.in);
	static int initialSpeed = 0;
	static int detectionInterval = 0;
	
	static void initialiseSystem() {
		
	}
	
	static void welcomeScreen() {
		System.out.println("=====================================================================");
		System.out.println(" ____           __  ______  _       _____   ____    __   __  ______ ");
		System.out.println(" \\ \\         / / |  ____|| |     / ____| / __ \\ |  \\/  ||  ____|");
		System.out.println("  \\ \\  /\\  / /  | |__   | |     | |     | |  | || \\  / || |__   ");
		System.out.println("   \\ \\/  \\/ /   |  __|  | |     | |     | |  | || |\\/| ||  __|  ");
		System.out.println("    \\   /\\  /    | |____ | |____ | |____ | |__| || |  |  || |____ ");
		System.out.println("     \\ /  \\/     |______||______|\\_____|\\____/ |_|  |__||______|");
		System.out.println("=====================================================================");
		System.out.println("SwiftBot Traffic Light Detection Program");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("This program makes the SwiftBot:");
		System.out.println("- Detect traffic light colours (RED, GREEN, BLUE)");
		System.out.println("- React to each light based on the colour");
		System.out.println("- Display statistics of the run through");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Press [A] on the SwiftBot to START");
		System.out.println("Press [X] on the SwiftBot to EXIT");
		System.out.println("=====================================================================");
	}
	
	static void speedMode() {
		System.out.println("=====================================================================");
		System.out.println("SPEED SELECTION");
		System.out.println("=====================================================================");
		System.out.println("Please choose one of the modes for the SwiftBot's initial speed:");
		System.out.println("[1] MODE 1: speed = 20%");
		System.out.println("[2] MODE 2: speed = 30%");
		System.out.println("[3] MODE 3: speed = 40%");
		System.out.println("---------------------------------------------------------------------");
		
		int choice = 0;
		
		while (choice < 1 || choice > 3) {
			System.out.println("Enter your choice (1-3)");
			String input = scanner.nextLine();
			
			if (input == "1") {
				choice = 1;
				initialSpeed = 20;
				detectionInterval = 750;
			}
			
			else if (input == "2") {
				choice = 2;
				initialSpeed  = 30;
				detectionInterval = 500;
			}
			
			else if (input == "3") {
				choice = 3;
				initialSpeed = 40;
				detectionInterval = 500;
			}
			
			else {
				System.out.println("==============================================================");
				System.out.println("ERROR: Invalid speed mode entered, please try again.");
				System.out.println("==============================================================");
				speedMode();
			}
		System.out.println("You have chosen speed mode " + choice + ". Speed is " +initialSpeed);
		}
		
	}
	
	static void detectLight() {
		
	}
	
	static void processImage() {
		
	}
	
	static void lightDetected() {
		
	}
	
	static void greenLight() {
		
	}
	
	static void blueLight() {
		
	}
	
	static void redLight() {
		
	}
	
	static void promptToContinue() {
		
	}
	
	static void createDisplayLog() {
		
	}
	
	static void displayLog() {
		
	}
	
	static void writeLogtoFile() {
		
	}
	
	
}
