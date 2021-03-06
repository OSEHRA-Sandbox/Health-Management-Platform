package org.osehra.cpe.jsonc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.osehra.cpe.datetime.jackson.HealthTimeModule;
import org.osehra.cpe.vpr.pom.POMUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * This class helps implement JSON-C response when the <code>data</code> container contains a collection of items.<p/>
 * In particular, it exposes the collection/paging related properties, including <code>items</code>,<code>totalItems</code>,<code>currentItemCount</code>
 *
 * @param <T>
 * @see "https://google-styleguide.googlecode.com/svn/trunk/jsoncstyleguide.xml"
 */
public class JsonCCollection<T> extends JsonCResponse<JsonCResponse.Collection<T>> {

    private static ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.registerModule(new HealthTimeModule());
    }

    public JsonCCollection() {
        // NOOP
    }

    public JsonCCollection(List<T> items) {
        this(new Collection(items));
    }

    public JsonCCollection(Page page) {
        this(new Collection(page));
    }

    public JsonCCollection(Collection<T> collection) {
        this.data = collection;
    }

    /**
     * The number of items in the result. This is not necessarily the size of the data.items array; if we are viewing the last page of items, the size of data.items may be less than itemsPerPage. However the size of data.items should not exceed itemsPerPage.
     */
    @JsonIgnore
    public Integer getItemsPerPage() {
        return this.data.itemsPerPage;
    }

    /**
     * The index of the first item in data.items. For consistency, startIndex should be 1-based. For example, the first item in the first set of items should have a startIndex of 1. If the user requests the next set of data, the startIndex may be 10.
     */
    @JsonIgnore
    public Integer getStartIndex() {
        return this.data.startIndex;
    }

    /**
     * The total number of items available in this set. For example, if a user has 100 blog posts, the response may only contain 10 items, but the totalItems would be 100.
     */
    @JsonIgnore
    public Integer getTotalItems() {
        return this.data.totalItems;
    }

    /**
     * The number of items in this result set. Should be equivalent to items.length, and is provided as a convenience property. For example, suppose a developer requests a set of search items, and asks for 10 items per page. The total set of that search has 14 total items. The first page of items will have 10 items in it, so both itemsPerPage and currentItemCount will equal "10". The next page of items will have the remaining 4 items; itemsPerPage will still be "10", but currentItemCount will be "4".
     */
    @JsonIgnore
    public Integer getCurrentItemCount() {
        return this.data.currentItemCount;
    }

    /**
     * This construct is intended to provide a standard location for collections related to the current result. For example, the JSON output could be plugged into a generic pagination system that knows to page on the items array. If items exists, it should be the last property in the data object.
     */
    @JsonIgnore
    public List<T> getItems() {
        return this.data.items;
    }

    @JsonIgnore
    public String getSelfLink() {
        return this.data.selfLink;
    }

    @JsonIgnore
    public String getEditLink() {
        return this.data.editLink;
    }

    @JsonIgnore
    public String getNextLink() {
        return this.data.nextLink;
    }

    @JsonIgnore
    public String getPreviousLink() {
        return this.data.previousLink;
    }

    public void setSelfLink(String href) {
        this.data.selfLink = href;
    }

    public void setEditLink(String href) {
        this.data.editLink = href;
    }

    public void setNextLink(String href) {
        this.data.nextLink = href;
    }

    public void setPreviousLink(String href) {
        this.data.previousLink = href;
    }

    @JsonIgnore
    public Map<String, Object> getAdditionalData() {
        return this.data.getAdditionalData();
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public void put(String key, Object value) {
        this.data.put(key, value);
    }

    public static <T> JsonCCollection<T> create(List<T> items) {
        return new JsonCCollection<T>(items);
    }

    public static <T> JsonCCollection<T> create(Page<T> page) {
        return new JsonCCollection<T>(page);
    }

    public static JsonCCollection<Map<String, Object>> create(InputStream inputStream) {
        return create(POMUtils.parseJSONtoNode(inputStream));
    }

    public static JsonCCollection<Map<String, Object>> create(String jsonString) {
        return create(POMUtils.parseJSONtoNode(jsonString));
    }

    public static JsonCCollection<Map<String, Object>> create(JsonNode node) {
        JsonCCollection<Map<String, Object>> response = JSON_MAPPER.convertValue(node, JsonCCollection.class);
        return response;
    }

    public static <T> JsonCCollection<T> create(HttpServletRequest request, List<T> items) {
        JsonCCollection<T> jsonc = new JsonCCollection<T>(items);
        jsonc.setMethodAndParams(request);
        return jsonc;
    }

    public static <T> JsonCCollection<T> create(HttpServletRequest request, Page<T> items) {
        JsonCCollection<T> jsonc = new JsonCCollection<T>(items);
        jsonc.setMethodAndParams(request);
        return jsonc;
    }
}
