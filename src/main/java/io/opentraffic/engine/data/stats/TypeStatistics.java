package io.opentraffic.engine.data.stats;

import io.opentraffic.engine.data.SpeedSample;

import java.io.Serializable;

public class TypeStatistics implements Serializable {

	private static final long serialVersionUID = -5885621202679850261l;

	public static final int MAX_TYPES = 6;

	public long sampleCount = 0;
	public double sampleSum = 0.0;

	public long typeSampleCount[] = new long[MAX_TYPES];
	public double typeSampleSum[] = new double[MAX_TYPES];

	public void addSample(SpeedSample ss) {
		sampleCount++;
		sampleSum += ss.getSpeed();

		//typeSampleCount[ss.getSegmentType()]++;
		//typeSampleSum[ss.getSegmentType()] += ss.getSpeed();
	}

	public void addStats(TypeStatistics stats) {
		this.sampleCount += stats.sampleCount;
		this.sampleSum += stats.sampleSum;

		for(int i = 0; i < MAX_TYPES; i++) {
			typeSampleCount[i] += stats.typeSampleCount[i];
			typeSampleSum[i] += stats.typeSampleSum[i];
		}
	}

	public void avgStats(TypeStatistics stats) {
		this.sampleCount++;
		this.sampleSum += stats.sampleSum / stats.sampleCount;

		for(int i = 0; i < MAX_TYPES; i++) {
			if(stats.typeSampleCount[i] > 0) {
				typeSampleCount[i]++;
				typeSampleSum[i] += stats.typeSampleSum[i] / stats.typeSampleCount[i];
			}
		}
	}
}
