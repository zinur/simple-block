package com.simple.block;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Block2 {

    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

    private int nonce;

    public Block2(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    public String calculateHash() {
        return HashingUtil.sha256(previousHash + timeStamp + nonce + data);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();

        }
        log.info("Block Mined!!! {} block nonce {}  {}", data, nonce, hash);
    }
}
