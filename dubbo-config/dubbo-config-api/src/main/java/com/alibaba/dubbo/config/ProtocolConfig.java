/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.status.StatusChecker;
import com.alibaba.dubbo.common.threadpool.ThreadPool;
import com.alibaba.dubbo.config.support.Parameter;
import com.alibaba.dubbo.remoting.Codec;
import com.alibaba.dubbo.remoting.Dispatcher;
import com.alibaba.dubbo.remoting.Transporter;
import com.alibaba.dubbo.remoting.exchange.Exchanger;
import com.alibaba.dubbo.remoting.telnet.TelnetHandler;
import com.alibaba.dubbo.rpc.Protocol;

import java.util.Map;

/**
 * ProtocolConfig
 * 参考 <a href="http://dubbo.apache.org/zh-cn/docs/user/references/xml/dubbo-protocol.html">dubbo:protocol</a>
 * @export
 */
public class ProtocolConfig extends AbstractConfig {

    private static final long serialVersionUID = 6913423882496634749L;

    // protocol name
    /**
     * 对应URL参数：<protocol> 带<>表明是<protocol>://<host>:<port>?key=value，如果是?后面的参数则直接是keyName
     * 是否必填：必填
     * 默认值：dubbo
     * 作用：性能调优
     * 描述：协议名称
     */
    private String name;

    // service IP address (when there are multiple network cards available)
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String host;

    // service port
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer port;

    // context path
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：缺省为空串
     * 作用：
     * 描述：
     */
    private String contextpath;

    // thread pool
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String threadpool;

    // thread pool size (fixed size)
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer threads;

    // IO thread pool size (fixed size)
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer iothreads;

    // thread pool's queue length
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer queues;

    // max acceptable connections
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer accepts;

    // protocol codec
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String codec;

    // serialization
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String serialization;

    // charset
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String charset;

    // payload max length
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Integer payload;

    // buffer size
    /**
     * 对应URL参数：buffer
     * 是否必填：可选
     * 默认值：8192
     * 作用：性能调优
     * 描述：网络读写缓冲区大小
     */
    private Integer buffer;

    // heartbeat interval
    /**
     * 对应URL参数：heartbeat
     * 是否必填：可选
     * 默认值：0
     * 作用：性能调优
     * 描述：心跳间隔，对于长连接，当物理层断开时，比如拔网线，TCP的FIN消息来不及发送，对方收不到断开事件，此时需要心跳来帮助检查连接是否已断开
     */
    private Integer heartbeat;

    // access log
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String accesslog;

    // transfort
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String transporter;

    // how information is exchanged
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String exchanger;

    // thread dispatch mode
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String dispatcher;

    // networker
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String networker;

    // sever impl
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String server;

    // client impl
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String client;

    // supported telnet commands, separated with comma.
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String telnet;

    // command line prompt
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String prompt;

    // status check
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String status;

    // whether to register
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Boolean register;

    // parameters
    // 是否长连接
    // TODO add this to provider config
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private Boolean keepAlive;

    // TODO add this to provider config
    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String optimizer;

    /**
     * 对应URL参数：
     * 是否必填：
     * 默认值：
     * 作用：
     * 描述：
     */
    private String extension;

    // parameters
    private Map<String, String> parameters;

    // if it's default
    private Boolean isDefault;

    public ProtocolConfig() {
    }

    public ProtocolConfig(String name) {
        setName(name);
    }

    public ProtocolConfig(String name, int port) {
        setName(name);
        setPort(port);
    }

    /**
     * 对于@Parameter注解的处理只涉及到了getter方法
     */
    @Parameter(excluded = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName("name", name);
        this.name = name;
        if (id == null || id.length() == 0) {
            id = name;
        }
    }

    @Parameter(excluded = true)
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        checkName("host", host);
        this.host = host;
    }

    @Parameter(excluded = true)
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Deprecated
    @Parameter(excluded = true)
    public String getPath() {
        return getContextpath();
    }

    @Deprecated
    public void setPath(String path) {
        setContextpath(path);
    }

    @Parameter(excluded = true)
    public String getContextpath() {
        return contextpath;
    }

    public void setContextpath(String contextpath) {
        checkPathName("contextpath", contextpath);
        this.contextpath = contextpath;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(String threadpool) {
        checkExtension(ThreadPool.class, "threadpool", threadpool);
        this.threadpool = threadpool;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getIothreads() {
        return iothreads;
    }

    public void setIothreads(Integer iothreads) {
        this.iothreads = iothreads;
    }

    public Integer getQueues() {
        return queues;
    }

    public void setQueues(Integer queues) {
        this.queues = queues;
    }

    public Integer getAccepts() {
        return accepts;
    }

    public void setAccepts(Integer accepts) {
        this.accepts = accepts;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        if ("dubbo".equals(name)) {
            checkMultiExtension(Codec.class, "codec", codec);
        }
        this.codec = codec;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        if ("dubbo".equals(name)) {
            checkMultiExtension(Serialization.class, "serialization", serialization);
        }
        this.serialization = serialization;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Integer getPayload() {
        return payload;
    }

    public void setPayload(Integer payload) {
        this.payload = payload;
    }

    public Integer getBuffer() {
        return buffer;
    }

    public void setBuffer(Integer buffer) {
        this.buffer = buffer;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        if ("dubbo".equals(name)) {
            checkMultiExtension(Transporter.class, "server", server);
        }
        this.server = server;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        if ("dubbo".equals(name)) {
            checkMultiExtension(Transporter.class, "client", client);
        }
        this.client = client;
    }

    public String getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }

    public String getTelnet() {
        return telnet;
    }

    public void setTelnet(String telnet) {
        checkMultiExtension(TelnetHandler.class, "telnet", telnet);
        this.telnet = telnet;
    }

    @Parameter(escaped = true)
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        checkMultiExtension(StatusChecker.class, "status", status);
        this.status = status;
    }

    public Boolean isRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        checkExtension(Transporter.class, "transporter", transporter);
        this.transporter = transporter;
    }

    public String getExchanger() {
        return exchanger;
    }

    public void setExchanger(String exchanger) {
        checkExtension(Exchanger.class, "exchanger", exchanger);
        this.exchanger = exchanger;
    }

    /**
     * typo, switch to use {@link #getDispatcher()}
     *
     * @deprecated {@link #getDispatcher()}
     */
    @Deprecated
    @Parameter(excluded = true)
    public String getDispather() {
        return getDispatcher();
    }

    /**
     * typo, switch to use {@link #getDispatcher()}
     *
     * @deprecated {@link #setDispatcher(String)}
     */
    @Deprecated
    public void setDispather(String dispather) {
        setDispatcher(dispather);
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        checkExtension(Dispatcher.class, "dispacther", dispatcher);
        this.dispatcher = dispatcher;
    }

    public String getNetworker() {
        return networker;
    }

    public void setNetworker(String networker) {
        this.networker = networker;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(String optimizer) {
        this.optimizer = optimizer;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void destroy() {
        if (name != null) {
            ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(name).destroy();
        }
    }
}
