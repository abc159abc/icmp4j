package org.icmp4j.platform.windows;

import java.util.List;
import java.util.LinkedList;

import junit.framework.TestCase;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.util.NetUtil;

/**
 * ShortPasta Foundation
 * http://www.shortpasta.org
 * Copyright 2009 and beyond, Sal Ingrilli at the ShortPasta Software Foundation
 * <p/>
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation as long as:
 * 1. You credit the original author somewhere within your product or website
 * 2. The credit is easily reachable and not burried deep
 * 3. Your end-user can easily see it
 * 4. You register your name (optional) and company/group/org name (required)
 * at http://www.shortpasta.org
 * 5. You do all of the above within 4 weeks of integrating this software
 * 6. You contribute feedback, fixes, and requests for features
 * <p/>
 * If/when you derive a commercial gain from using this software
 * please donate at http://www.shortpasta.org
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
 * Date: Sep 14, 2016
 * Time: 4:15:24 PM
 */
public class WindowsProcessNativeBridgeTest extends TestCase {

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_parse_win7_normal () {

    final List<String> stringList = new LinkedList<String> ();
    stringList.add ("");
    stringList.add ("Pinging www.google.com [216.58.217.196] with 32 bytes of data:");
    stringList.add ("Reply from 216.58.217.196: bytes=32 time=12ms TTL=56");
    stringList.add ("");
    stringList.add ("Ping statistics for 216.58.217.196:");
    stringList.add ("    Packets: Sent = 1, Received = 1, Lost = 0 (0% loss),");
    stringList.add ("Approximate round trip times in milli-seconds:");
    stringList.add ("    Minimum = 12ms, Maximum = 12ms, Average = 12ms");
    stringList.add ("");
    debug ("output:");
    for (final String string : stringList) {
      debug ("string: " + string);
    }

    final WindowsProcessNativeBridge nativeBridge = new WindowsProcessNativeBridge ();
    final IcmpPingResponse response = nativeBridge.executePingRequest (stringList);

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
        "216.58.217.196 == " + host,
        "216.58.217.196",
        host);
    }

    {
      final int rtt = response.getRtt ();
      debug ("rtt: " + rtt);
      assertEquals (
        12 + " == " + rtt,
        12,
        rtt);
    }

    {
      final int size = response.getSize ();
      debug ("size: " + size);
      assertEquals (
        32 + " == " + size,
        32,
        size);
    }

    {
      final int ttl = response.getTtl ();
      debug ("ttl: " + ttl);
      assertEquals (
        56 + " == " + ttl,
        56,
        ttl);
    }

    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        true + " == " + successFlag,
        true,
        successFlag);
    }
  }
  
  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_parse_win7_normal_Dmitry_Ilyushko_2017_03_28 () {

    final List<String> stringList = new LinkedList<String> ();
    stringList.add ("");
    stringList.add ("Pinging 192.168.0.238 from 192.168.0.77 with 32 bytes of data:");
    stringList.add ("Reply from 192.168.0.238: bytes=32 time=4ms TTL=64");
    stringList.add ("");
    stringList.add ("Ping statistics for 192.168.0.238:");
    stringList.add ("Packets: Sent = 1, Received = 1, Lost = 0 (0% loss),");
    stringList.add ("Approximate round trip times in milli-seconds:");
    stringList.add ("Minimum = 4ms, Maximum = 4ms, Average = 4ms");
    stringList.add ("");
    debug ("output:");
    for (final String string : stringList) {
      debug ("string: " + string);
    }

    final WindowsProcessNativeBridge nativeBridge = new WindowsProcessNativeBridge ();
    final IcmpPingResponse response = nativeBridge.executePingRequest (stringList);

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
        "192.168.0.238 == " + host,
        "192.168.0.238",
        host);
    }

    {
      final int rtt = response.getRtt ();
      debug ("rtt: " + rtt);
      assertEquals (
        4 + " == " + rtt,
        4,
        rtt);
    }

    {
      final int size = response.getSize ();
      debug ("size: " + size);
      assertEquals (
        32 + " == " + size,
        32,
        size);
    }

    {
      final int ttl = response.getTtl ();
      debug ("ttl: " + ttl);
      assertEquals (
        64 + " == " + ttl,
        64,
        ttl);
    }

    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        true + " == " + successFlag,
        true,
        successFlag);
    }
  }

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_parse_win7_localhost () {

    final List<String> stringList = new LinkedList<String> ();
    stringList.add ("");
    stringList.add ("Pinging 127.0.0.1 with 32 bytes of data:");
    stringList.add ("Reply from 127.0.0.1: bytes=32 time<1ms TTL=128");
    stringList.add ("");
    stringList.add ("Ping statistics for 127.0.0.1:");
    stringList.add ("    Packets: Sent = 1, Received = 1, Lost = 0 (0% loss),");
    stringList.add ("Approximate round trip times in milli-seconds:");
    stringList.add ("    Minimum = 0ms, Maximum = 0ms, Average = 0ms");
    stringList.add ("");
    debug ("output:");
    for (final String string : stringList) {
      debug ("string: " + string);
    }

    final WindowsProcessNativeBridge nativeBridge = new WindowsProcessNativeBridge ();
    final IcmpPingResponse response = nativeBridge.executePingRequest (stringList);

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
        "127.0.0.1 == " + host,
        "127.0.0.1",
        host);
    }

    {
      final int rtt = response.getRtt ();
      debug ("rtt: " + rtt);
      assertEquals (
        1 + " == " + rtt,
        1,
        rtt);
    }

    {
      final int size = response.getSize ();
      debug ("size: " + size);
      assertEquals (
        32 + " == " + size,
        32,
        size);
    }

    {
      final int ttl = response.getTtl ();
      debug ("ttl: " + ttl);
      assertEquals (
        128 + " == " + ttl,
        128,
        ttl);
    }

    {
      final boolean successFlag = response.getSuccessFlag ();
      debug ("successFlag: " + successFlag);
      assertEquals (
        true + " == " + successFlag,
        true,
        successFlag);
    }
  }

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_live_win7_normal () {
  
    // delegate
    final String source = null;
    executePingRequest (source);
  }

  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_live_win7_normalBySource () {
  
    // delegate
    final String source = NetUtil.findFirstLocalHostIpv4Address ();
    executePingRequest (source);
  }
  
  /**
   * Tests executePingRequest ()
   */
  public void test_executePingRequest_live_win7_normalByBadSource () {
  
    // request
    final IcmpPingRequest request = new IcmpPingRequest ();
    request.setSource ("5.5.5.5");
    request.setHost ("www.google.com");
    request.setTimeout (1000);

    // delegate
    final WindowsProcessNativeBridge nativeBridge = new WindowsProcessNativeBridge ();
    debug ("pinging www.google.com with source: 5.5.5.5");
    final IcmpPingResponse response = nativeBridge.executePingRequest (request);
    debug ("response: " + response);
    final boolean successFlag = response.getSuccessFlag ();
    assertFalse ("successFlag", successFlag); 
  }

  /**
   * Executes the given test
   * @param source
   */
  private void executePingRequest (final String source) {

    // request
    final IcmpPingRequest request = new IcmpPingRequest ();
    request.setSource (source);
    request.setHost ("www.google.com");
    request.setTimeout (5000);

    // delegate
    final WindowsProcessNativeBridge nativeBridge = new WindowsProcessNativeBridge ();
    debug ("pinging www.google.com 5 times");
    for (int index = 0; index < 5; index ++) {

      // iterate
      debug ("ping #" + (index + 1));
      final IcmpPingResponse response = nativeBridge.executePingRequest (request);
      debug ("response: " + response);
      final boolean successFlag = response.getSuccessFlag ();
      assertTrue ("successFlag", successFlag); 
    }
  }

  /**
   * Uniformly logs the given debug string
   * @param string
   */
  private void debug (final String string) {

    System.out.println ("<DEBUG> <" + string + ">");
  }
}