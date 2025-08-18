package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

public class LocationFinder {
    private final Map<Long, Location> locations = new HashMap();

    public static class Location {
        private final int column;
        private final int row;

        public Location(int i, int i2) {
            this.row = i;
            this.column = i2;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public LocationFinder(NeuronSquareMesh2D neuronSquareMesh2D) {
        int numberOfRows = neuronSquareMesh2D.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D.getNumberOfColumns();
        for (int i = 0; i < numberOfRows; i++) {
            int i2 = 0;
            while (i2 < numberOfColumns) {
                Long valueOf = Long.valueOf(neuronSquareMesh2D.getNeuron(i, i2).getIdentifier());
                if (this.locations.get(valueOf) == null) {
                    this.locations.put(valueOf, new Location(i, i2));
                    i2++;
                } else {
                    throw new MathIllegalStateException();
                }
            }
        }
    }

    public Location getLocation(Neuron neuron) {
        return this.locations.get(Long.valueOf(neuron.getIdentifier()));
    }
}
