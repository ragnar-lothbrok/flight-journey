package com.ixigo.flights.services;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ixigo.flights.models.Airport;

/**
 * This will index & search data in Lucene
 * @author raghunandangupta
 *
 */
@Service
public class TextIndexAndSearchService {

	private final static Logger logger = LoggerFactory.getLogger(TextIndexAndSearchService.class);

	@Autowired
	private NGramAnalyzer nGramAnalyzer;

	private IndexSearcher indexSearcher;

	@Autowired
	private AirPortService airPortService;
	
	private static Map<String,Float> boostMap;
	
	static{
		boostMap = new HashMap<String,Float>();
		boostMap.put("airportCode", 4.0f);
		boostMap.put("cityName", 2.0f);
	}

	public void index(List<Airport> airPortList) {
		try {
			if (airPortList != null && airPortList.size() > 0) {
				airPortList = airPortList.stream().filter(ap -> (!searchByAirPortCode(ap.getAirportCode()))).collect(Collectors.toList());
				if (airPortList.size() > 0) {
					Path path = FileSystems.getDefault().getPath("/tmp/");
					IndexWriter indexWriter = new IndexWriter(FSDirectory.open(path), new IndexWriterConfig(nGramAnalyzer));
					for (Airport ap : airPortList) {
						Document document = new Document();
						document.add(new TextField("airportCode", ap.getAirportCode(), Store.YES));
						document.add(new TextField("cityName", ap.getCityName(), Store.YES));
						document.add(new TextField("airportName", ap.getAirportName(), Store.YES));
						indexWriter.addDocument(document);
					}
					indexWriter.close();
				}
			}
		} catch (Exception exception) {
			logger.error("Exception occured while indexing AirPorts {}", exception);
		}
	}

	public Boolean searchByAirPortCode(String airPortCode) {
		try {
			setIndexSearcher();
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[] { "airportCode" }, nGramAnalyzer);
			Query query = queryParser.parse(airPortCode);
			TopDocs results = indexSearcher.search(query, 1);
			if (results.totalHits != 0) {
				return true;
			}
		} catch (Exception exception) {
			logger.error("Exception occured while fetching airport {}", exception);
		}
		return false;
	}

	private void setIndexSearcher() throws IOException {
		if (this.indexSearcher == null) {
			Path path = FileSystems.getDefault().getPath("/tmp/");
			Directory directory = FSDirectory.open(path);
			IndexReader indexReader = DirectoryReader.open(directory);
			this.indexSearcher = new IndexSearcher(indexReader);
		}
	}

	public List<Airport> search(String searchString) {
		List<Airport> searchedAirPortList = new ArrayList<Airport>();
		Airport airport = new Airport();
		List<Airport> inputAirPortList = airPortService.allAirports();
		int index = -1;
		try {
			setIndexSearcher();
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[] { "airportCode", "cityName", "airportName" }, nGramAnalyzer,boostMap);
			Query query = queryParser.parse(searchString);
			TopDocs result = indexSearcher.search(query, 12);
			logger.info("# of results:" + result.totalHits);
			for (ScoreDoc scoreDoc : result.scoreDocs) {
				Document doc = indexSearcher.doc(scoreDoc.doc);
				airport.setAirportCode(doc.get("airportCode"));
				index = inputAirPortList.indexOf(airport);
				if (index != -1) {
					searchedAirPortList.add(inputAirPortList.get(index));
				}
			}

		} catch (Exception exception) {
			logger.error("Exception occured while indexing AirPorts {}", exception);
		}
		return searchedAirPortList;
	}
}
