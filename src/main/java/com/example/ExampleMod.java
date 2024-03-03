package com.example;

import net.fabricmc.api.ModInitializer;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		executeLuaScript("test.lua");
	}
	private void executeLuaScript(String fileName) {
        try {
            // Create a Lua interpreter
            LuaValue globals = JsePlatform.standardGlobals();
			String script = "luamods/" + fileName;
            //LuaValue chunk = globals.load(new FileReader(new File(fileName)), "main.lua");
			//LuaValue chunk = globals.load(new FileInputStream(fileName), "main.lua");
			LuaValue chunk = globals.loadfile(script);

            // Execute the Lua script
            chunk.call();
        } catch (IOException e) {
            System.err.println("Failed to read Lua script: " + e.getMessage());
        } catch (LuaError e) {
            System.err.println("Lua script execution error: " + e.getMessage());
        }
    }
}