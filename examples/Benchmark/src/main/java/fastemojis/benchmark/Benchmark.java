package fastemojis.benchmark;

import fastemojis.FastEmojis;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Benchmark {

    private int emojiCodepoint;
    private int asciiCodepoint;
    private int cjkCodepoint;

    @Setup
    public void setup() {
        emojiCodepoint = 0x1F600; // 😀
        asciiCodepoint = 'A';
        cjkCodepoint = 0x4E00; // 一
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int benchmarkEmojiWidth() {
        return FastEmojis.getWidth(emojiCodepoint);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int benchmarkAsciiWidth() {
        return FastEmojis.getWidth(asciiCodepoint);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int benchmarkCjkWidth() {
        return FastEmojis.getWidth(cjkCodepoint);
    }
}
