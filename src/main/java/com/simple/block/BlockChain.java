package com.simple.block;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockChain {

    public static final int difficulty = 5;

    public static final List<Block> blocks = Lists.newArrayList();

    public static void main(String[] args) {


        Block genesisBlock = new Block("Hi im the first block", "0");
        blocks.add(genesisBlock);
        blocks.get(0).mineBlock(difficulty);
        for (int i = 0; i < 2; i++) {
            Block secondBlock = new Block(String.format("Hey im the %s block", i+1),blocks.get(blocks.size()-1).hash);
            blocks.add(secondBlock);
            secondBlock.mineBlock(difficulty);

        }



        log.info("Blocks is valid  : {}", isChainValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocks);
        log.info("Blocks : {}",blockChainJson);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
