package com.ixigo.flights.services;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseTokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.springframework.stereotype.Service;

@Service
public class NGramAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new LowerCaseTokenizer();
		TokenStream filter = new NGramTokenFilter(tokenizer, 1, 5);
		return new TokenStreamComponents(tokenizer, filter);
	}

}
