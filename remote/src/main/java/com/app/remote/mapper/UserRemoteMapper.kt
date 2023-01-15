package com.app.remote.mapper

import com.app.common.Mapper
import com.app.data.model.UserDataModel
import com.app.remote.model.UserRemoteModel
import javax.inject.Inject

class UserRemoteMapper @Inject constructor() : Mapper<UserRemoteModel, UserDataModel> {
    override fun from(i: UserRemoteModel?): UserDataModel {
        return UserDataModel(
            login = i?.login,
            id = i?.id,
            node_id = i?.node_id,
            avatar_url = i?.avatar_url,
            gravatar_id = i?.gravatar_id,
            url = i?.url,
            html_url = i?.html_url,
            followers_url = i?.followers_url,
            following_url = i?.following_url,
            gists_url = i?.gists_url,
            starred_url = i?.starred_url,
            subscriptions_url = i?.subscriptions_url,
            organizations_url = i?.organizations_url,
            repos_url = i?.repos_url,
            events_url = i?.events_url,
            received_events_url = i?.received_events_url,
            type = i?.type,
            site_admin = i?.site_admin
        )
    }

    override fun to(o: UserDataModel?): UserRemoteModel {
        return UserRemoteModel(
            login = o?.login,
            id = o?.id,
            node_id = o?.node_id,
            avatar_url = o?.avatar_url,
            gravatar_id = o?.gravatar_id,
            url = o?.url,
            html_url = o?.html_url,
            followers_url = o?.followers_url,
            following_url = o?.following_url,
            gists_url = o?.gists_url,
            starred_url = o?.starred_url,
            subscriptions_url = o?.subscriptions_url,
            organizations_url = o?.organizations_url,
            repos_url = o?.repos_url,
            events_url = o?.events_url,
            received_events_url = o?.received_events_url,
            type = o?.type,
            site_admin = o?.site_admin
        )
    }
}