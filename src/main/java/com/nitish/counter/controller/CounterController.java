package com.nitish.counter.controller;

import com.csvreader.CsvWriter;
import com.nitish.counter.dto.CountDto;
import com.nitish.counter.service.CountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(basePath = "/counter-api", value = "user", description = "User Controller")
@RestController
@RequestMapping(value = "/counter-api")
public class CounterController {

    private static final String POST_OPS_VALUE = " Used for search the the text frequency on server side";
    private static final String POST_OPS_NOTES = " should be pass the data in Key,Value Pair " +
            " value must be list of words we need to search on server ";
    private static final String GET_OPS_VALUE = "used to calculate the frequency of word on server";
    private static final String GET_OPS_NOTES = " pass the count, which will return the top count result to client";


    @Autowired
    private CountService countService;



    @RequestMapping(value = "/search", method = POST, consumes = "application/json")
    @ApiOperation(value = POST_OPS_VALUE,
            notes = POST_OPS_NOTES)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "User already exists"),
            @ApiResponse(code = 500, message = "Internal server error, please contact support")
    })
    public ResponseEntity<Collection<CountDto>> search(@RequestBody Map<String, List<String>> searchTexts) {
        return new ResponseEntity<>(countService.countOccuranceFor(searchTexts), OK);
    }

    @RequestMapping(value = "/top/{count}", method = GET, consumes = "application/json", produces = "text/csv")
    @ApiOperation(value = GET_OPS_VALUE,
            notes = GET_OPS_NOTES)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "No User Found")
    })
    public void topText(@PathVariable int count, HttpServletResponse response) throws IOException {
        Collection<CountDto> higherOccuranceText = countService.getHigherCountTextUpTo(count);
        response.setHeader("Content-Disposition", "attachment; filename=topText.csv");
        csvWrite(higherOccuranceText, response.getOutputStream());

    }

    private void csvWrite(Collection<CountDto> higherOccuranceText, ServletOutputStream outputStream) throws IOException {
        OutputStream buffOs = new BufferedOutputStream(outputStream);
        OutputStreamWriter outputwriter = new OutputStreamWriter(buffOs);

        CsvWriter writer = new CsvWriter(outputwriter, '\u0009');
        for (CountDto countDto : higherOccuranceText) {
            writer.write(countDto.getWord() + "|" + countDto.getCount() + System.getProperty("line.separator"));
        }
        outputwriter.flush();
        outputwriter.close();
    }

}
