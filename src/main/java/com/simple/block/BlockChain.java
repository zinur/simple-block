package com.simple.block;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockChain {

//    public static final int difficulty = 4;

    public static final List<Block> blocks = Lists.newArrayList();

    public static void main(String[] args) {

        Block genesisBlock = new Block("Genesis block", "0");

        blocks.add(genesisBlock);
        for (int i = 0; i < 3; i++) {
            Block nextBlock = new Block(String.format("Block # %s ", i + 1), blocks.get(blocks.size() - 1).getHash());
            blocks.add(nextBlock);
        }
        log.info("Blocks is valid  : {}", validateIntegrity());
        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocks);
        log.info("Blocks : {}", blockChainJson);

    }

    public static boolean validateIntegrity() {
        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                log.error("Current Hashes not equal");
                return Boolean.FALSE;
            }
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                log.error("Previous Hashes not equal");
                return Boolean.FALSE;
            }
//            //check if hash is solved
//            String hashTarget = String.format("%0" + difficulty + "d", 0);
//            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
//                log.error("This block hasn't been mine");
//                return Boolean.FALSE;
//            }
        }

        return Boolean.TRUE;

    }
}
