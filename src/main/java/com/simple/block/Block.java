package com.simple.block;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;

    public Block(final String data,final String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    public String calculateHash() {
        return HashingUtil.sha256(previousHash + timeStamp + data);
    }
}
