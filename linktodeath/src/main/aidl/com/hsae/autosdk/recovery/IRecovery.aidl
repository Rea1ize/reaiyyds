// IRecovery.aidl
package com.hsae.autosdk.recovery;

// Declare any non-default types here with import statements
import com.hsae.autosdk.recovery.IRecoveryCallback;

interface IRecovery {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     void registerRecoveryCallback(IRecoveryCallback dl);
     void unregisterRecoveryCallback(IRecoveryCallback dl);


}