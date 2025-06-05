package org.icmp4j.platform.linux;

import java.util.Arrays;
import java.util.List;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.exception.ParseException;
import org.icmp4j.platform.unix.LinuxProcessNativeBridge;

import junit.framework.TestCase;

/**
 * Internet Control Message Protocol for Java (ICMP4J)
 * http://www.icmp4j.org
 * Copyright 2009 and beyond, icmp4j
 * <p/>
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation as long as:
 * 1. You credit the original author somewhere within your product or website
 * 2. The credit is easily reachable and not burried deep
 * 3. Your end-user can easily see it
 * 4. You register your name (optional) and company/group/org name (required)
 * at http://www.icmp4j.org
 * 5. You do all of the above within 4 weeks of integrating this software
 * 6. You contribute feedback, fixes, and requests for features
 * <p/>
 * If/when you derive a commercial gain from using this software
 * please donate at http://www.icmp4j.org
 * <p/>
 * If prefer or require, contact the author specified above to:
 * 1. Release you from the above requirements
 * 2. Acquire a commercial license
 * 3. Purchase a support contract
 * 4. Request a different license
 * 5. Anything else
 * <p/>
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, similarly
 * to how this is described in the GNU Lesser General Public License.
 * <p/>
 * User: Sal Ingrilli
 * Date: May 23, 2014
 * Time: 10:46:34 PM
 */
public class LinuxNativeBridgeTest extends TestCase {

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_normal () {

    // delegate
    final LinuxProcessNativeBridge linuxProcessNativeBridge = createLinuxProcessNativeBridge(
      "PING www.google.com (74.125.224.211) 32(60) bytes of data.",
      "40 bytes from lax02s02-in-f19.1e100.net (74.125.224.211): icmp_req=1 ttl=56 time=47.2 ms");
    final IcmpPingRequest request = new IcmpPingRequest();
    final IcmpPingResponse response = linuxProcessNativeBridge.executePingRequest (request);

    {
      final String errorMessage = response.getErrorMessage ();
      debug ("errorMessage: " + errorMessage);
      assertEquals (
        null + " == " + errorMessage,
        null,
        errorMessage);
    }

    {
      final String host = response.getHost ();
      debug ("host: " + host);
      assertEquals (
        "lax02s02-in-f19.1e100.net == " + host,
        "lax02s02-in-f19.1e100.net",
        host);
    }
    
    {
      final int rtt = response.getRtt ();
      debug ("rtt: " + rtt);
      assertEquals (
        47 + " == " + rtt,
        47,
        rtt);
    }
    
    {
      final int size = response.getSize ();
      debug ("size: " + size);
      assertEquals (
        40 + " == " + size,
        40,
        size);
    }
    
    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        true + " == " + successFlag,
        true,
        successFlag);
    }
    
    {
      final int ttl = response.getTtl ();
      debug ("ttl: " + ttl);
      assertEquals (
        56 + " == " + ttl,
        56,
        ttl);
    }
  }
  
  /**
   * Tests executePingRequest ()
   * WARNING: the difference with these Linux distributions is that for the sequence number
   * they use "icmp_seq" and NOT "icmp_req"
   */
  public void test_executePingRequest_Mint17_and_ArchLinux () {

    final LinuxProcessNativeBridge linuxProcessNativeBridge = createLinuxProcessNativeBridge(
      "PING www.google.com (74.125.224.211) 32(60) bytes of data.",
      "40 bytes from lax02s02-in-f19.1e100.net (74.125.224.211): icmp_seq=1 ttl=56 time=47.2 ms");
    final IcmpPingRequest request = new IcmpPingRequest();
    final IcmpPingResponse response = linuxProcessNativeBridge.executePingRequest (request);

    {
      final String errorMessage = response.getErrorMessage ();
      debug ("errorMessage: " + errorMessage);
      assertEquals (
        null + " == " + errorMessage,
        null,
        errorMessage);
    }

    {
      final String host = response.getHost ();
      debug ("host: " + host);
      assertEquals (
        "lax02s02-in-f19.1e100.net == " + host,
        "lax02s02-in-f19.1e100.net",
        host);
    }
    
    {
      final int rtt = response.getRtt ();
      debug ("rtt: " + rtt);
      assertEquals (
        47 + " == " + rtt,
        47,
        rtt);
    }
    
    {
      final int size = response.getSize ();
      debug ("size: " + size);
      assertEquals (
        40 + " == " + size,
        40,
        size);
    }
    
    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        true + " == " + successFlag,
        true,
        successFlag);
    }
    
    {
      final int ttl = response.getTtl ();
      debug ("ttl: " + ttl);
      assertEquals (
        56 + " == " + ttl,
        56,
        ttl);
    }
  }
  
  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_unknownHost () {

    final LinuxProcessNativeBridge linuxProcessNativeBridge = createLinuxProcessNativeBridge(
      "ping: unknown host www.googgle.com");
    final IcmpPingRequest request = new IcmpPingRequest();
    final IcmpPingResponse response = linuxProcessNativeBridge.executePingRequest (request);

    {
      final String errorMessage = response.getErrorMessage ();
      debug ("errorMessage: " + errorMessage);
      assertEquals (
        "ping: unknown host www.googgle.com" + " == " + errorMessage,
        "ping: unknown host www.googgle.com",
        errorMessage);
    }
    
    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        false + " == " + successFlag,
        false,
        successFlag);
    }
  }

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_destinationHostUnreachable () {

    // sat 2/13/2021, 4:25 pm pacific
    // reported by Darlan Ullmann: https://sourceforge.net/p/icmp4j/discussion/general/thread/eb5bde09/
    // ping -c 1 -s 56 -w 1 192.168.32.9
    // PING 192.168.32.9 (192.168.32.9) 56(84) bytes of data.
    // From 177.84.139.27 icmp_seq=1 Destination Host Unreachable
    //
    // --- 192.168.32.9 ping statistics ---
    // 1 packets transmitted, 0 received, +1 errors, 100% packet loss, time 0ms
    final LinuxProcessNativeBridge linuxProcessNativeBridge = createLinuxProcessNativeBridge(
      "PING 192.168.32.9 (192.168.32.9) 56(84) bytes of data.",
      "From 177.84.139.27 icmp_seq=1 Destination Host Unreachable",
      "--- 192.168.32.9 ping statistics ---",
      "1 packets transmitted, 0 received, +1 errors, 100% packet loss, time 0ms");
    final IcmpPingRequest request = new IcmpPingRequest();
    final IcmpPingResponse response = linuxProcessNativeBridge.executePingRequest (request);

    {
      final String errorMessage = response.getErrorMessage ();
      debug ("errorMessage: " + errorMessage);
      assertEquals (
        "Destination Host Unreachable" + " == " + errorMessage,
        "Destination Host Unreachable",
        errorMessage);
    }

    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        false + " == " + successFlag,
        false,
        successFlag);
    }
  }

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_parseException () {

    // sat 2/13/2021, 4:46 pm pacific
    // detect any parsing issues, whether ours or because of bad command output, and make sure
    // that we report them as ParseExceptions
    // ping -c 1 -s 56 -w 1 192.168.32.9
    // PING 192.168.32.9 (192.168.32.9) 56(84) bytes of data.
    // From 177.84.139.27 icmp_seq=1 xxxxxxxxxxxxxxxxxx
    final LinuxProcessNativeBridge linuxProcessNativeBridge = createLinuxProcessNativeBridge(
      "PING 192.168.32.9 (192.168.32.9) 56(84) bytes of data.",
      "From 177.84.139.27 icmp_seq=1 xxxxxxxxxxxxxxxxxx");
    boolean expectedExceptionCaughtFlag = false;
    try {
      final IcmpPingRequest request = new IcmpPingRequest ();
      final IcmpPingResponse response = linuxProcessNativeBridge.executePingRequest (request);
      debug("response: " + response);
    }
    catch(final ParseException e) {
      debug("caught expected ParseException. message: " + e.getMessage());
      expectedExceptionCaughtFlag = true;
    }
    assertTrue("expected ParseException", expectedExceptionCaughtFlag);
  }

  /**
   * Uniformly creates a LinuxProcessNativeBridge that mocks the output to the given lineArray
   * @param lineArray
   * @return LinuxProcessNativeBridge
   */
  private LinuxProcessNativeBridge createLinuxProcessNativeBridge(final String... lineArray) {
    return new LinuxProcessNativeBridge () {
      @Override
      protected List<String> executeProcessAndGetOutputAsStringList(final String command, final String charsetName) {
        debug ("simulated command output:");
        for (final String line : lineArray) {
          debug ("line: " + line);
        }
        return Arrays.asList(lineArray);
      }
    };
  }

  /**
   * Uniformly logs the given debug string
   * @param string
   */
  private void debug (final String string) {
    
    System.out.println ("<DEBUG> <" + string + ">");
  }
}