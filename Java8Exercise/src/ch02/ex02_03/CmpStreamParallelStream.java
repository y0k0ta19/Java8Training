package ch02.ex02_03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CmpStreamParallelStream {
/*
 * resut:
 *         Stream: 95323836
 * ParallelStream: 17766112
 */
	public void cmp(String path) throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\P{L}+"));
		
		//シングルスレッドのタイム計測
		long start = System.nanoTime();
		words.stream().filter(w ->  w.length() > 12).count();
		long end = System.nanoTime();
		System.out.println("        Stream: " + (end - start));
		//マルチスレッドのタイム計測
		start = System.nanoTime();
		words.parallelStream().filter(w ->  w.length() > 12).count();
		end = System.nanoTime();
		System.out.println("ParallelStream: " + (end - start));
	}

	public static void main(String[] args) {
		CmpStreamParallelStream csps = new CmpStreamParallelStream();
		try {
			csps.cmp("src/ch02/ex02_03/LongAlice.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
