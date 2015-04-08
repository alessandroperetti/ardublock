package com.ardublock.translator.block.Esplora;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class File_Read extends TranslatorBlock
{
	public File_Read(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()  throws SocketNullException, SubroutineNotDeclaredException
	{
		
		String Variable;
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		Variable = translatorBlock.toCode();
		
		translator.addHeaderFile("Esplora.h");
		translator.addHeaderFile("SPI.h");
		translator.addHeaderFile("SD.h");
	    translator.addSetupCommand("\tconst int chipSelect = 8;\n\tSD.begin(chipSelect);\n");
		String ret = Variable + ".read()";
	    
		return codePrefix + ret + codeSuffix;
	}

}
