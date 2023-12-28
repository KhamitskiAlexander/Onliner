package by.onliner.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CollectionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionHelper.class);

    public static List<Integer> getIndecesOfMaxValuesInList(final List<Integer> list) {
        List<Integer> maxIndices = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            if (current > max) {
                maxIndices.clear();
                maxIndices.add(i);
                max = current;
            } else if (current == max) {
                maxIndices.add(i);
            }
        }
        LOGGER.info("Indices of max values in list are: {}", Arrays.toString(maxIndices.toArray()));
        return maxIndices;
    }
}
