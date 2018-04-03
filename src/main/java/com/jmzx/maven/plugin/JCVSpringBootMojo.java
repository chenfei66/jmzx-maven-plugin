package com.jmzx.maven.plugin;

import java.io.File;

import com.jmzx.maven.plugin.domain.JCVConfig;
import com.jmzx.maven.plugin.domain.YUIConfig;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.jmzx.maven.plugin.support.ProcessFactory;
import com.jmzx.maven.plugin.support.SpringBootProcessFactory;
import com.jmzx.maven.plugin.support.logger.LoggerFactory;
import com.jmzx.maven.plugin.support.logger.MavenLoger;
import com.jmzx.maven.plugin.utils.FileUtils;

/**  
 * 
 * spring boot process
 *  
* @Package 
*	 com.iqarr.maven.plugin
* @ClassName: 
*	 JCVSpringBootMojo  
* @since 
*	  V1.0
* @author 
*		zhangyong   
* @version 
*		V1.0      
*/
@Mojo( name = "process-springboot" , defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class JCVSpringBootMojo extends BaseMojo {

    
    /**
     * webapp目录
     */
    @Parameter( defaultValue = "${basedir}/src/main/resources/", property = "webappDirectory", required = true )
    protected File webappDirectory;
    
    /*
    * <p>Title: execute</p>
    * <Description: 
    * @throws MojoExecutionException
    * @throws MojoFailureException  
    * @see org.apache.maven.plugin.Mojo#execute()  
    */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        LoggerFactory.buildLogerFactory (new  MavenLoger(getLog ()));
        
        //config set
        webRootName="classes";
        

        String webRoot=webappDirectory.getPath();
        if(!webRoot.endsWith(FileUtils.getSystemLineSpearator())){
            webRoot+=FileUtils.getSystemFileSeparator();
        }
        
        String outJsCssRoot="";
        if(null!=outJSCSSDirPath &&!"".equals(outJSCSSDirPath) ){
            outJsCssRoot=outJSCSSDirPath;
            
        }else {
            outJsCssRoot=outputDirectory.getPath()+FileUtils.getSystemFileSeparator()+webRootName;
        }
        JCVConfig jcvConfig=new JCVConfig ();
        jcvConfig.setPageSuffixs (suffixs);
        jcvConfig.setJsMethod (globaJsMethod);
        jcvConfig.setCssMethod (globaCssMethod);
        jcvConfig.setVersionLable (versionLable);
        jcvConfig.setBaseCssDomin (baseCssDomin);
        jcvConfig.setBaseJsDomin (baseJsDomin);
        jcvConfig.setGlobaCsslPrefixPath (globaCsslPrefixPath);
        jcvConfig.setGlobaJslPrefixPath (globaJslPrefixPath);
        jcvConfig.setSourceEncoding (sourceEncoding);
        jcvConfig.setClearPageComment (clearPageComment);
        jcvConfig.setOutJSCSSDirPath (outJsCssRoot);
        jcvConfig.setCompressionCss (compressionCss);
        jcvConfig.setCompressionJs (compressionJs);
        jcvConfig.setUserCompressionSuffix (userCompressionSuffix);
        jcvConfig.setExcludesCss (excludesCss);
        jcvConfig.setExcludesJs (excludesJs);
        if(yuiConfig==null){
            yuiConfig=new YUIConfig();
        }
        jcvConfig.setYuiConfig (yuiConfig);
        jcvConfig.setSkipFileNameSuffix (skipFileNameSuffix);
        
        //version 6.1
        if(null!=jsConstantName &&!"".equals(jsConstantName)){
            jcvConfig.setJsConstantName(jsConstantName);
        }
        if(null!=cssConstantName &&!"".equals(cssConstantName)){
            jcvConfig.setCssConstantName(cssConstantName);
        }
        
        if(null!=jsConstantAliasPath &&!"".equals(jsConstantAliasPath)){
            jcvConfig.setJsConstantAliasPath(jsConstantAliasPath);
        }
        
        if(null!=cssConstantAliasPath && !"".equals(cssConstantAliasPath)){
            jcvConfig.setCssConstantAliasPath(cssConstantAliasPath);
        }
        
        if(-1!=versionValLenth){
            jcvConfig.setVersionValLenth(versionValLenth);
        }
        
        String out= outputDirectory.getPath()+FileUtils.getSystemFileSeparator()+webRootName;
        jcvConfig.setOutDirRoot (out);
       ProcessFactory processFactory=new SpringBootProcessFactory(jcvConfig);
       processFactory.initDisplayInfo ();
       getLog().info("build webRootName:"+webRootName);
       //显示日志
        getLog().info("web app Dir:"+webappDirectory.getPath());
       processFactory.initJcv (webRoot);
       processFactory.doProcessPageFile ();
       processFactory.displaySuccessInfo ();
       
        
    }
    
}
