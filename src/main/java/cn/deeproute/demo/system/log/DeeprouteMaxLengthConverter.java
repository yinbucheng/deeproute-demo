package cn.deeproute.demo.system.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.pattern.*;
import org.apache.logging.log4j.util.PerformanceSensitive;

import java.util.List;

@Plugin(name = "deeprouteMaxLength", category = PatternConverter.CATEGORY)
@ConverterKeys({"deeprouteMaxLength", "deeprouteMaxLen"})
@PerformanceSensitive("allocation")
public final class DeeprouteMaxLengthConverter extends LogEventPatternConverter {

    /**
     * Gets an instance of the class.
     *
     * @param config  The current Configuration.
     * @param options pattern options, an array of two elements: pattern, max length (defaults to 100 on invalid value).
     * @return instance of class.
     */
    public static DeeprouteMaxLengthConverter newInstance(final Configuration config, final String[] options) {
        if (options.length != 2) {
            LOGGER.error("Incorrect number of options on maxLength: expected 2 received {}: {}", options.length,
                    options);
            return null;
        }
        if (options[0] == null) {
            LOGGER.error("No pattern supplied on maxLength");
            return null;
        }
        if (options[1] == null) {
            LOGGER.error("No length supplied on maxLength");
            return null;
        }
        final PatternParser parser = PatternLayout.createPatternParser(config);
        final List<PatternFormatter> formatters = parser.parse(options[0]);
        return new DeeprouteMaxLengthConverter(formatters, AbstractAppender.parseInt(options[1], 100));
    }

    /**
     * 修改最大长度
     *
     * @param maxLength
     */
    public static void setMaxLength(int maxLength) {
        DeeprouteMaxLengthConverter.maxLength = maxLength;
    }


    private final List<PatternFormatter> formatters;

    private static volatile int maxLength;

    /**
     * Construct the converter.
     *
     * @param formatters The PatternFormatters to generate the text to manipulate.
     * @param maxLength  The max. length of the resulting string. Ellipsis ("...") is appended on shorted string, if greater than 20.
     */
    private DeeprouteMaxLengthConverter(final List<PatternFormatter> formatters, final int maxLength) {
        super("DeeprouteMaxLength", "deeprouteMaxLength");
        DeeprouteMaxLengthConverter.maxLength = maxLength;
        this.formatters = formatters;
        LOGGER.trace("new MaxLengthConverter with {}", maxLength);
    }


    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final int initialLength = toAppendTo.length();
        for (int i = 0; i < formatters.size(); i++) {
            final PatternFormatter formatter = formatters.get(i);
            formatter.format(event, toAppendTo);
            if (toAppendTo.length() > initialLength + maxLength) {
                break;
            }
        }
        if (toAppendTo.length() > initialLength + maxLength) {
            toAppendTo.setLength(initialLength + maxLength);
            //如果未打印完增加...
            toAppendTo.append("...");
        }
    }
}