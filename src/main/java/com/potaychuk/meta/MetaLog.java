package com.potaychuk.meta;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
public interface MetaLog {
    String SDLSIG_REGEX = "^.{28}SdlSig\\s.*";
    String STOPPING_REGEX = "^.{28}Stopping.*";
    long TIME_TO_WAIT = 300_000L;
}
