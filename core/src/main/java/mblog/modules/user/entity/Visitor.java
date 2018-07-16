/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.modules.user.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * 訪問記錄
 *
 * @author langhsu
 *
 */
@Entity
@Table(name = "mto_visitor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Visitor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ip", length = 64)
	private String ip; // IP地址

	@Column(name = "access_time", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date accessTime; // 訪問时间

	@Column(name = "country", length = 64)
	String country ; // 國家

	@Column(name = "country_id", length = 64)
	String countryId ; // 國家語言

	@Column(name = "region", length = 64)
	String region  ; // 省

	@Column(name = "city", length = 64)
	String city ; // 市

	@Column(name = "isp", length = 64)
	String isp ; // 網絡


	public Visitor() { }

	public Visitor(String ip, Date accessTime) {
		this.ip = ip;
		this.accessTime = accessTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	@Override
	public String toString() {
		return "Visitor{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", accessTime=" + accessTime +
				", country='" + country + '\'' +
				", countryId='" + countryId + '\'' +
				", region='" + region + '\'' +
				", city='" + city + '\'' +
				", isp='" + isp + '\'' +
				'}';
	}
}
