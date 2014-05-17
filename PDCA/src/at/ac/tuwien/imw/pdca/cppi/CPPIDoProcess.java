package at.ac.tuwien.imw.pdca.cppi;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.ac.tuwien.imw.pdca.DoProcess;
import at.ac.tuwien.imw.pdca.cppi.service.CPPIService;

public class CPPIDoProcess extends DoProcess implements Closeable {

	private final static Logger log = LogManager.getLogger(DoProcess.class);
	private static CPPIDoProcess instance;

	private boolean running = true;

	private CPPIDoProcess() {
		doRules = new CPPIDoRules();
	}

	public static synchronized CPPIDoProcess getInstance() {
		if (instance == null) {
			instance = new CPPIDoProcess();
		}
		return instance;
	}

	@Override
	public void run() {
	  try {
	    synchronized(this){
	      wait();
	    }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		while (running) {
			/*
		  try {
			 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			*/
			log.info("Do Process");
			operate();
			CPPIService.getInstance().notifyCheck();
			try {
			  synchronized(this){
	        wait();
	        System.out.println("service awake");
	      }
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
		}
	}

	@Override
	public void operate() {
		doRules.applyDoRules();
	}

	@Override
	public void close() throws IOException {
		running = false;

	}

}
