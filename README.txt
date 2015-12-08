To run:
    With either android studio or android sdk for eclipse, open up the frugal application
	
	If you have a galaxy s4, it'll run best on that as I developed for that phone. If not, I supplied
		materials such that you can can run the emulator. See below
		
	Start the application and enjoy
	
To run the s4 emulator:
	Start the application. This will open up a window asking if you have a phone or emulator you would like to use
	
	select emulator and to the right of the dropdown, click the button with 3 dots.
	
	This will open a new window in the Android Virtual Device Manager. Click Create Virtual Device
	
	From here, there are many Google preset devices, but we need a samsung one. Click "Import Hardware Profiles"
	
	In the repository, there is a file called "s4_device.xml" Import that file
	
	Under the phone tab, you will see a device name "s4". Select that and click next
	
	Select the lolipop 5.0.1 OS with API level 21 and click next
	
	Click finished and the s4 will be ready to use (hopefully)
	
	Click ok, and you will be back on the choose device page. Select the s4 from the virtual device dropdown and start the app
	
If you get an error saying the emulation require hardware acceleration, here is how you solve it:
	Open SDK Manager and Download Intel x86 Emulator Accelerator (HAXM installer) if you haven't under extras.

	Now go to your SDK directory \users\username\AppData\Local\Android\sdk. In this directory Go to extras > intel > Hardware_Accelerated_Execution_Manager and run the file named "intelhaxm-android.exe".

	In case you get an error like "Intel virtualization technology (vt,vt-x) is not enabled". Go to your BIOS settings and enable Hardware Virtualization.
	Restart Android Studio and then try to start the AVD again.

	It might take a minute or 2 to show the emulator window.
	
	
Link to the repository:
	https://github.com/frugalApp/frugalHighFid.git
	https://github.com/frugalApp/frugalHighFid
	
	