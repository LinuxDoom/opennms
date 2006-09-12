package org.opennms.web.svclayer.dao.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.opennms.netmgt.config.SiteStatusViewsFactory;
import org.opennms.netmgt.config.siteStatusViews.View;
import org.opennms.netmgt.config.siteStatusViews.Views;
import org.opennms.web.svclayer.dao.SiteStatusViewConfigDao;
import org.springframework.dao.DataRetrievalFailureException;

public class DefaultSiteStatusViewConfigDao implements SiteStatusViewConfigDao {
    
    static {
        try {
            SiteStatusViewsFactory.init();
        } catch (MarshalException e) {
            throw new DataRetrievalFailureException("Syntax error in site status view config file", e);
        } catch (ValidationException e) {
            throw new DataRetrievalFailureException("Syntax error in site status view config file", e);
        } catch (FileNotFoundException e) {
            throw new DataRetrievalFailureException("Unable to locate site status view config file", e);
        } catch (IOException e) {
            throw new DataRetrievalFailureException("Error load site status view config file", e);
        }
    }
    
    public DefaultSiteStatusViewConfigDao() {
    }

    public View getView(String viewName) {
        try {
            return SiteStatusViewsFactory.getInstance().getView(viewName);
        } catch (MarshalException e) {
            throw new DataRetrievalFailureException("Syntax error in site status view config file", e);
        } catch (ValidationException e) {
            throw new DataRetrievalFailureException("Syntax error in site status view config file", e);
        } catch (IOException e) {
            throw new DataRetrievalFailureException("Error load site status view config file", e);
        }
    }

    /**
     * Use this method to get the generated Views class generated by the XSD.
     */
    public Views getViews() {
        return SiteStatusViewsFactory.getConfig().getViews();
    }
    
    /**
     * Use this method to get a Map of view names to marshalled classes based on the generated View class
     * from the XSD.
     * @return <code>Map</> of View classes.
     */
    public Map<String, View> getViewMap() {
        return SiteStatusViewsFactory.getViewsMap();
    }

    public View getDefaultView() {
        final String defaultView = SiteStatusViewsFactory.getConfig().getDefaultView();
        return getView(defaultView);
    }

}
