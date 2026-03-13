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
	
	static int[] red = {255, 0, 0};
	static int[] blue = {0, 0, 255};
	static int[] green = {0, 255, 0};
	static int[] yellow = {255, 255, 0};
	static int[] off = {0, 0, 0};
	
	static SwiftBotAPI robot = SwiftBotAPI.INSTANCE;
	
	static Scanner scanner = new Scanner (System.in);
	
	static int initialSpeed = 0;
	static int detectionInterval = 0;
	
	static boolean isRunning = true;
	
	static String detectedColour = "";
	static double detectedDistance = 0.0;
	
	static int redCount = 0;
	static int greenCount = 0;
	static int blueCount = 0;
	
	static int lightCount = 0;
	
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
	
	static void loop() {
		while (isRunning == true) {
			detectLight();
			if (isRunning == false) {
				break;
			}
			lightDetected();
			if (isRunning == false) {
				break;
			}
			lightCount = lightCount + 1;
			
			if (lightCount % 3 == 0) {
				promptToContinue();
			}
		}
		robot.stopMove();
	}
	
	static void detectLight() {
		System.out.println("==============================================================");
		System.out.println("Setting underlights to YELLOW");
		System.out.println("==============================================================");
		robot.fillUnderlights(yellow);
		System.out.println("SwiftBot is now moving forward at " + initialSpeed + "%");
		System.out.println("Scanning for traffic lights...");
		
		robot.startMove(initialSpeed, initialSpeed);
		
		boolean islightFound = false;
		
		while (isRunning == true && islightFound == false) {
			try {
				Thread.sleep(detectionInterval);
			} catch (InterruptedException e) {
				System.out.println("Error during scanning: " + e.getMessage());
			}
			
			BufferedImage img = robot.takeStill(ImageSize.SQUARE_480x480);
			
			double distance = robot.useUltrasound();
			
			if (distance <= 30.0) {
				
			}
			
		}
		
	}
	
	static String processImage(BufferedImage img) {
		if (img == null) {
			return null;
		}
		
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		int pixelTotal = imgHeight * imgWidth;
		
		int totalR = 0;
		int totalB = 0;
		int totalG = 0;
		
		for (int i = 0; i < imgHeight; i++) {
			for (int j = 0; j < imgWidth; j++) {
				int pixel = img.getRGB(j, i);
				int r = (pixel >> 16) & 0xFF;
				int g = (pixel >> 8) & 0xFF;
				int b = pixel & 0xFF;
				
				totalR = totalR + r;
				totalG = totalG + g;
				totalB = totalB + b;
			}
		}
		double avgR = totalR/pixelTotal;
		double avgG = totalG/pixelTotal;
		double avgB = totalB/pixelTotal;
		
		if (avgR > avgG && avgR > avgB) {
			return "red";
		}
		else if (avgG > avgB && avgG > avgR) {
			return "green";
		}
		else if (avgB > avgG && avgB > avgR) {
			return "blue";
		}
		
		return null;
	}
	
	static void lightDetected() {
		System.out.println("==============================================================");
		System.out.println("TRAFFIC LIGHT DETECTED");
		System.out.println("==============================================================");
		System.out.println("Colour: " + detectedColour.toUpperCase());
		System.out.println("Distance: " + detectedDistance + " cm");
		
		if (detectedColour == "red") {
			redLight();
		}
		else if (detectedColour == "green") {
			greenLight();
		}
		else if (detectedColour == "blue") {
			blueLight();
		}
	}
	
	static void redLight() {
		System.out.println("SwiftBot stopping...");
		robot.fillUnderlights(red);
		robot.stopMove();
		redCount = redCount + 1;
		
		try {
			Thread.sleep(1000);
			System.out.println("Moving forward again...");
		} catch (InterruptedException e) {
			System.out.println("Red light error: " + e.getMessage());
		}
	}
	
	static void greenLight() {
		System.out.println("Passing through green light...");
		robot.fillUnderlights(green);
		greenCount = greenCount + 1;
		
		try {
			robot.move(initialSpeed, initialSpeed, 2000);
			System.out.println("Stopping.");
			Thread.sleep(1000);
			robot.fillUnderlights(yellow);
			System.out.println("Underlights set to yellow. Moving forward again...");
		}
		catch (InterruptedException e) {
			System.out.println("Green light error: " + e.getMessage());
		}
	}
	
	static void blueLight() {
		System.out.println("Yielding path...");
		robot.stopMove();
		blueCount = blueCount + 1;
		
		try {
			Thread.sleep(1000);
			for (int i = 0; i < 3; i++) {
				robot.fillUnderlights(blue);
				Thread.sleep(500);
				robot.fillUnderlights(off);
				Thread.sleep(400);
			}
			
			robot.move(-initialSpeed, initialSpeed, 500);
			Thread.sleep(100);
			
			robot.move(initialSpeed, initialSpeed, 1000);
			Thread.sleep(1000);
			
			robot.move(initialSpeed, -initialSpeed, 500);
			Thread.sleep(100);
			
			robot.move(-initialSpeed, -initialSpeed, 1000);
			
			System.out.println("Continuing movements...");
			robot.fillUnderlights(yellow);
		}
		catch (InterruptedException e) {
			System.out.println("Blue light error: " + e.getMessage());
		}
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
