package com.wy.mydemo.model;

import java.io.Serializable;
import java.util.Date;

public class SysJobLog extends BaseEntity implements Serializable {
    private Long jobLogId;

    private String jobName;

    private String jobGroup;

    private String invokeTarget;

    private String jobMessage;

    private String status;

    private String exceptionInfo;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getJobLogId() {
        return jobLogId;
    }

    public void setJobLogId(Long jobLogId) {
        this.jobLogId = jobLogId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget == null ? null : invokeTarget.trim();
    }

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage == null ? null : jobMessage.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobLogId=").append(jobLogId);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobGroup=").append(jobGroup);
        sb.append(", invokeTarget=").append(invokeTarget);
        sb.append(", jobMessage=").append(jobMessage);
        sb.append(", status=").append(status);
        sb.append(", exceptionInfo=").append(exceptionInfo);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}