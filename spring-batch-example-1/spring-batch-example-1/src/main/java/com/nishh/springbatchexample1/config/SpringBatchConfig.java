package com.nishh.springbatchexample1.config;

import org.springframework.core.io.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nishh.springbatchexample1.model.User;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	//

	@Bean
	public Job job(JobBuilderFactory builderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<User> itemReader,
			ItemProcessor<User, User> itemProcessor, ItemWriter<User> itemWriter) {

		Step step = stepBuilderFactory.get("ETL-file-load").<User, User>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();

		return builderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();// use flow when
																										// there are
																										// many
																										// step and then
																										// use
																										// flow.build();
	}

	@Bean
	public FlatFileItemReader<User> fileItemReader(@Value("${input}") Resource resource) {
		FlatFileItemReader<User> flatItemReader = new FlatFileItemReader<>();
		flatItemReader.setResource(resource);
		flatItemReader.setName("CSV-READER");
		flatItemReader.setLinesToSkip(1);
		flatItemReader.setLineMapper(linemapper());
		return flatItemReader;
	}

	@Bean
	public LineMapper<User> linemapper() {

		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] { "id", "name", "dept", "salary" });

		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

		fieldSetMapper.setTargetType(User.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}
}
